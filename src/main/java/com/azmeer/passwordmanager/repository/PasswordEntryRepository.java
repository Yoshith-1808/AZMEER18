package com.azmeer.passwordmanager.repository;

import com.azmeer.passwordmanager.entity.PasswordEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PasswordEntryRepository extends JpaRepository<PasswordEntry, Long> {
    // Spring auto-creates these methods:
    // save(), findAll(), findById(), delete(), etc.

    List<PasswordEntry> findByUserId(Long userId);
}
