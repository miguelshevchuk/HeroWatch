package ar.com.gha.cryptoghastarter.infrastructure.security.crypto;

/**
 * Unchecked exception to represent encryption/decryption failures.
 */
public class CryptoException extends RuntimeException {
    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }
}
