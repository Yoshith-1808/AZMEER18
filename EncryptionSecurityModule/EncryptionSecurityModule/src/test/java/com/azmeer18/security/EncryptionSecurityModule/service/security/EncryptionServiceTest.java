package com.azmeer18.security.encryptionsecuritymodule.service.security;

import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionServiceTest {

    @Test
    void encryptDecryptTest() throws Exception {
        EncryptionService service = new EncryptionService();

        // Use a sample key for testing
        SecretKey key = service.generateKey("1234567890123456".getBytes());
        String original = "MySecretPassword";

        String encrypted = service.encrypt(original, key);
        String decrypted = service.decrypt(encrypted, key);

        assertEquals(original, decrypted, "Decrypted password should match the original");
    }
}