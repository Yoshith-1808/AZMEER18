package com.azmeer18.security.encryptionsecuritymodule.service.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MasterPasswordVerificationServiceTest {

    @Test
    void hashAndVerifyPasswordTest() {
        MasterPasswordVerificationService service = new MasterPasswordVerificationService();

        String password = "MyStrongPass123!";
        String hash = service.hashMasterPassword(password);

        assertTrue(service.verifyMasterPassword(password, hash), "Correct password should verify");
        assertFalse(service.verifyMasterPassword("WrongPassword", hash), "Incorrect password should fail verification");
    }
}