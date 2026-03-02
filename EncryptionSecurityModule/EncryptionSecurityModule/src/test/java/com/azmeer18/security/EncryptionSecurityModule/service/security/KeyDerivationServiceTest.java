package com.azmeer18.security.encryptionsecuritymodule.service.security;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import static org.junit.jupiter.api.Assertions.*;

class KeyDerivationServiceTest {

    @Test
    void keyDerivationConsistencyTest() throws Exception {
        KeyDerivationService service = new KeyDerivationService();
        String password = "MyMasterPass!";
        byte[] salt = "RandomSalt12345".getBytes();

        SecretKey key1 = service.deriveKey(password, salt);
        SecretKey key2 = service.deriveKey(password, salt);

        assertArrayEquals(key1.getEncoded(), key2.getEncoded(), "Keys derived from same password+salt must match");

        SecretKey key3 = service.deriveKey("DifferentPass", salt);
        assertFalse(java.util.Arrays.equals(key1.getEncoded(), key3.getEncoded()),
                "Keys for different passwords must differ");
    }
}