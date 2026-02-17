package ar.com.gha.cryptoghastarter.infrastructure.config;

import ar.com.gha.cryptoghastarter.infrastructure.security.crypto.AesGcmCryptoService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(CryptoProperties.class)
public class CryptoAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AesGcmCryptoService aesGcmCryptoService(CryptoProperties props) {
        return new AesGcmCryptoService(props);
    }
}
