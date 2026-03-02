package com.azmeer.passwordmanager.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class EncryptionService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 1. Hash Master Password (BCrypt)
    public String hashMasterPassword(String masterPassword) {
        return passwordEncoder.encode(masterPassword);
    }

    // 2. Verify Master Password
    public boolean verifyMasterPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    // 3. Derive AES Key from Master Password (secure key derivation)
    public SecretKey deriveEncryptionKey(String masterPassword) {
        try {
            // SHA-256 hash of master password → 256-bit AES key
            byte[] hash = MessageDigest.getInstance("SHA-256")
                    .digest(masterPassword.getBytes(StandardCharsets.UTF_8));
            return new SecretKeySpec(hash, "AES");
        } catch (Exception e) {
            throw new RuntimeException("Key derivation failed", e);
        }
    }

    // 4. Encrypt Password (AES) - for vault storage
    public String encryptPassword(String plainPassword, String masterPassword) {
        try {
            SecretKey key = deriveEncryptionKey(masterPassword);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(plainPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    // 5. Decrypt Password (requires master password re-entry)
    public String decryptPassword(String encryptedPassword, String masterPassword) {
        try {
            SecretKey key = deriveEncryptionKey(masterPassword);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(encryptedPassword);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed - wrong master password?", e);
        }
    }
}
