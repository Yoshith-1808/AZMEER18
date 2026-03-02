package com.azmeer.passwordmanager.service;

import com.azmeer.passwordmanager.entity.PasswordEntry;
import com.azmeer.passwordmanager.repository.PasswordEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SecurityAuditService {

    @Autowired
    private PasswordEntryRepository repository;

    @Autowired
    private PasswordStrengthService strengthService;

    public AuditReport generateReport(Long userId) {
        List<PasswordEntry> entries = repository.findByUserId(userId);

        long weakCount = entries.stream()
                .filter(e -> strengthService.isWeak(e.getEncryptedPassword()))  // Real: decrypt first
                .count();

        long oldCount = entries.stream()
                .filter(e -> ChronoUnit.DAYS.between(e.getDateAdded(), LocalDateTime.now()) > 90)
                .count();

        // Reused detection (simplified - group by username/website)
        long reusedCount = (long) entries.stream()
                .map(e -> e.getUsername() + ":" + e.getWebsiteUrl())
                .distinct()
                .count() < entries.size() ? 1 : 0;  // Simplified

        return new AuditReport(weakCount, reusedCount, oldCount, entries.size());
    }

    // Record for JSON response
    public record AuditReport(long weak, long reused, long old, long total) {}
}
