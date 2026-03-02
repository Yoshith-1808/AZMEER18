package com.azmeer18.security.encryptionsecuritymodule.service.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

class SecurityAuditServiceTest {

    @Test
    void securityAuditTest() {
        SecurityAuditService service = new SecurityAuditService();

        List<String> passwords = Arrays.asList(
                "12345", // weak
                "Abcd1234!", // strong
                "Password123!", // reused example
                "OldPass!@#" // old (>90 days)
        );

        List<String> weak = service.checkWeakPasswords(passwords);
        assertTrue(weak.contains("12345"));

        List<String> reused = service.checkReusedPasswords(Arrays.asList("Abcd1234!", "Abcd1234!"));
        assertTrue(reused.contains("Abcd1234!"));

        List<String> old = service.checkOldPasswords(Arrays.asList("OldPass!@#"));
        assertTrue(old.contains("OldPass!@#"));
    }
}