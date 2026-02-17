package ar.com.gha.cryptoghastarter.infrastructure.config;

import ar.com.gha.cryptoghastarter.infrastructure.security.crypto.AesGcmCryptoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.*;

import java.util.*;

public class DecryptingEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final Logger log = LoggerFactory.getLogger(DecryptingEnvironmentPostProcessor.class);

    private static final String ENC_PREFIX = "ENC(";
    private static final String ENC_SUFFIX = ")";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment env, SpringApplication application) {
        log.info("Activando el procesador de propiedades criptografadas");
        // 1) obtener key AES desde env/property (antes de bind)
        String aesKeyB64 = env.getProperty("gha.crypto.aesKey");
        if ((aesKeyB64 == null || aesKeyB64.isBlank())) {
            // fallback a variable de entorno (p. ej. cuando aún no cargó application.yml)
            aesKeyB64 = System.getenv("AES_KEY");
        }
        if (aesKeyB64 == null || aesKeyB64.isBlank()) {
            log.debug("No se encontró clave AES (gha.crypto.aesKey / AES_KEY). El post-processor no se activará.");
            return; // no activar
        }

        AesGcmCryptoService aesGcmCryptoService = new AesGcmCryptoService(aesKeyB64);
        // 2) recorrer PropertySources y preparar overrides descifrados
        Map<String, Object> overrides = new HashMap<>();

        for (PropertySource<?> ps : env.getPropertySources()) {
            if (!(ps instanceof EnumerablePropertySource<?> eps)) continue;

            for (String name : eps.getPropertyNames()) {
                Object val = ps.getProperty(name);
                if (!(val instanceof String s)) continue;

                String trimmed = s.trim();
                if (isEncrypted(trimmed)) {
                    String payload = trimmed.substring(ENC_PREFIX.length(), trimmed.length() - ENC_SUFFIX.length());
                    // Evitar intentar descifrar placeholders (p.ej. ENC(${DB_USERNAME:...}))
                    if (payload.contains("${")) {
                        String[] property = payload
                                .substring(payload.indexOf("${") + 2, payload.indexOf("}"))
                                .split(":");
                        if(Objects.nonNull(System.getenv(property[0]))){
                            payload = System.getenv(property[0]);
                        }else{
                            payload = property[1];
                        }
                    }
                    try {
                        String plain = aesGcmCryptoService.decryptFromBase64(payload);
                        overrides.put(name, plain);
                    } catch (RuntimeException ex) {
                        log.warn("No se pudo descifrar la propiedad '{}': {}. Se mantiene el valor original.", name, ex.getMessage());
                    }
                }
            }
        }

        if (!overrides.isEmpty()) {
            // 3) meter un PropertySource al principio para que pise a los demás
            env.getPropertySources().addFirst(new MapPropertySource("decrypted-overrides", overrides));
            log.info("{} propiedades ENC() fueron descifradas y aplicadas", overrides.size());
        } else {
            log.debug("No se encontraron propiedades ENC() para descifrar");
        }
    }

    private boolean isEncrypted(String s) {
        return s.startsWith(ENC_PREFIX) && s.endsWith(ENC_SUFFIX) && s.length() > (ENC_PREFIX.length() + ENC_SUFFIX.length());
    }

    @Override
    public int getOrder() {
        // Ejecutar inmediatamente DESPUÉS de que ConfigDataEnvironmentPostProcessor (ORDER = HIGHEST_PRECEDENCE + 10)
        // haya cargado application.yml y demás fuentes de configuración.
        // De este modo, podemos ver valores ENC(...) presentes en dichas fuentes y sobreescribirlos ya descifrados.
        return Ordered.HIGHEST_PRECEDENCE + 11;
    }
}
