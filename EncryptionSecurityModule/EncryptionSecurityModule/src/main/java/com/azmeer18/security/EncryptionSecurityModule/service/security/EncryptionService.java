package com.azmeer18.security.encryptionsecuritymodule.service.security;

import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncryptionService {

    // TODO: Replace with secure key derivation
    private static final String ALGO = "AES";

    // Encrypt a plain text string
    public String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt an encrypted string
    public String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(encryptedText);
        return new String(cipher.doFinal(decoded));
    }

    // Generate AES key from bytes (simple demo, replace with PBKDF2 in prod)
    public SecretKey generateKey(byte[] keyBytes) {
        return new SecretKeySpec(keyBytes, ALGO);
    }
}