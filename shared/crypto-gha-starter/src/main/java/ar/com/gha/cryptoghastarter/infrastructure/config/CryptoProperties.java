package ar.com.gha.cryptoghastarter.infrastructure.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Crypto-related configuration properties for the starter.
 *
 * Expected property: `gha.crypto.aesKey` with a Base64-encoded AES key (128/192/256 bits).
 */
@ConfigurationProperties(prefix = "gha.crypto")
public class CryptoProperties {

    /**
     * Base64-encoded AES key. Recommended size: 256 bits (32 bytes after Base64 decoding).
     */
    private String aesKey;

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }
}
