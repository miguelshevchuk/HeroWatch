package ar.com.gha.cryptoghastarter.infrastructure.security.crypto;

import ar.com.gha.cryptoghastarter.infrastructure.config.CryptoProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES-GCM encryption/decryption service.
 *
 * Best practices applied:
 * - AES in GCM mode (authenticated encryption)
 * - 12-byte random IV per encryption, unique and unpredictable
 * - 128-bit authentication tag
 * - Key provided via configuration as Base64 (supports 128/192/256-bit keys)
 * - Prepend IV to ciphertext; encode full payload in Base64 for textual transport
 */
public class AesGcmCryptoService {

    private static final Logger log = LoggerFactory.getLogger(AesGcmCryptoService.class);

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final int IV_LENGTH_BYTES = 12; // recommended for GCM

    private final SecureRandom secureRandom = new SecureRandom();
    private final SecretKey secretKey;

    public AesGcmCryptoService(CryptoProperties properties) {
        String b64 = properties.getAesKey();
        this.secretKey = new SecretKeySpec(buildKeyBytes(b64), "AES");
    }

    public AesGcmCryptoService(String b64){
        // No log of the key to avoid leaking secrets
        this.secretKey = new SecretKeySpec(buildKeyBytes(b64), "AES");
    }

    private byte[] buildKeyBytes(String b64){
        if (b64 == null || b64.isBlank()) {
            throw new CryptoException("AES key is not configured. Please set 'gha.crypto.aesKey' with a Base64-encoded key (128/192/256-bit)");
        }
        byte[] keyBytes;
        try {
            keyBytes = Base64.getDecoder().decode(b64);
        } catch (IllegalArgumentException e) {
            throw new CryptoException("Configured AES key is not valid Base64", e);
        }
        int len = keyBytes.length;
        if (len != 16 && len != 24 && len != 32) {
            throw new CryptoException("AES key must be 16, 24, or 32 bytes (128/192/256-bit). Actual: " + len + " bytes");
        }

        return keyBytes;
    }

    // --------------- Public API (String helpers) ---------------

    /**
     * Encrypts a UTF-8 string and returns Base64 string of (IV || ciphertext+tag).
     */
    public String encryptToBase64(String plaintext) {
        if (plaintext == null) return null;
        byte[] ct = encrypt(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(ct);
    }

    /**
     * Decrypts a Base64 string created by {@link #encryptToBase64(String)} and returns a UTF-8 string.
     */
    public String decryptFromBase64(String base64Ciphertext) {
        if (base64Ciphertext == null) return null;
        byte[] payload;
        try {
            payload = Base64.getDecoder().decode(base64Ciphertext);
        } catch (IllegalArgumentException e) {
            throw new CryptoException("Ciphertext is not valid Base64", e);
        }
        byte[] pt = decrypt(payload);
        return new String(pt, StandardCharsets.UTF_8);
    }

    // --------------- Core byte[] API ---------------

    /**
     * Encrypts bytes using AES-GCM and returns (IV || ciphertext+tag).
     */
    public byte[] encrypt(byte[] plaintext) {
        if (plaintext == null) return null;
        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
            byte[] ciphertext = cipher.doFinal(plaintext);

            ByteBuffer bb = ByteBuffer.allocate(iv.length + ciphertext.length);
            bb.put(iv);
            bb.put(ciphertext);
            return bb.array();
        } catch (GeneralSecurityException e) {
            throw new CryptoException("Encryption error", e);
        }
    }

    /**
     * Decrypts bytes in the format (IV || ciphertext+tag) produced by {@link #encrypt(byte[])}.
     */
    public byte[] decrypt(byte[] ivPlusCiphertext) {
        if (ivPlusCiphertext == null) return null;
        if (ivPlusCiphertext.length < IV_LENGTH_BYTES + 16) { // at least IV + 16 bytes of ct/tag
            throw new CryptoException("Ciphertext too short");
        }
        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            byte[] ciphertext = new byte[ivPlusCiphertext.length - IV_LENGTH_BYTES];
            System.arraycopy(ivPlusCiphertext, 0, iv, 0, IV_LENGTH_BYTES);
            System.arraycopy(ivPlusCiphertext, IV_LENGTH_BYTES, ciphertext, 0, ciphertext.length);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
            return cipher.doFinal(ciphertext);
        } catch (GeneralSecurityException e) {
            throw new CryptoException("Decryption error", e);
        }
    }

    // --------------- Utilities ---------------

    /**
     * Generates a new random AES key of the requested size and returns it Base64-encoded.
     * This is a helper to facilitate creating a secure key to set in configuration.
     */
    public String generateNewBase64Key(int keySizeBits) {
        if (keySizeBits != 128 && keySizeBits != 192 && keySizeBits != 256) {
            throw new IllegalArgumentException("keySizeBits must be 128, 192, or 256");
        }
        byte[] key = new byte[keySizeBits / 8];
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
