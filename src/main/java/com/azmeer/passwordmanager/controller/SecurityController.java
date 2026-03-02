package com.azmeer.passwordmanager.controller;

import com.azmeer.passwordmanager.service.EncryptionService;
import com.azmeer.passwordmanager.service.PasswordStrengthService;  // FIXED!
import com.azmeer.passwordmanager.service.SecurityAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private PasswordStrengthService strengthService;

    @Autowired
    private SecurityAuditService auditService;

    @PostMapping("/encrypt")
    public EncryptResponse encrypt(@RequestBody EncryptRequest request) {
        String encrypted = encryptionService.encryptPassword(
                request.password(), request.masterPassword());
        return new EncryptResponse(encrypted);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody EncryptRequest request) {
        return encryptionService.decryptPassword(
                request.password(), request.masterPassword());
    }

    @GetMapping("/strength/{password}")
    public String strength(@PathVariable String password) {
        return strengthService.analyzeStrength(password);
    }

    @GetMapping("/audit/1")
    public SecurityAuditService.AuditReport audit() {
        return auditService.generateReport(1L);
    }

    public record EncryptRequest(String masterPassword, String password) {}
    public record EncryptResponse(String encryptedPassword) {}
}
