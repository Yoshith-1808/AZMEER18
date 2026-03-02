package com.azmeer18.security.encryptionsecuritymodule.service.security;

import org.springframework.stereotype.Service;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;

@Service
public class KeyDerivationService {

    // Derive a key from master password (PBKDF2)
    public SecretKey deriveKey(char[] masterPassword, byte[] salt, int iterations, int keyLength) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(masterPassword, salt, iterations, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecretKey key = factory.generateSecret(spec);
        return key;
    }
}