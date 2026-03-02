package com.azmeer18.security.encryptionsecuritymodule.service.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordStrengthServiceTest {

    @Test
    void passwordStrengthTest() {
        PasswordStrengthService service = new PasswordStrengthService();

        assertEquals("Weak", service.evaluateStrength("12345"));
        assertEquals("Medium", service.evaluateStrength("abcd1234"));
        assertEquals("Strong", service.evaluateStrength("Abcd1234!"));
        assertEquals("Very Strong", service.evaluateStrength("AbcD1234!@#$"));
    }
}