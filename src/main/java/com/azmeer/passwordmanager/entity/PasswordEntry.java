package com.azmeer.passwordmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity                    // ← This makes it a database TABLE
@Table(name = "password_entries")
@Data                     // ← Auto generates getters/setters
public class PasswordEntry {

    @Id                        // ← Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountName;

    private String websiteUrl;

    private String username;

    private String encryptedPassword;  // AES encrypted password

    @Enumerated(EnumType.STRING)
    private Category category;

    private String notes;

    private boolean isFavorite = false;

    private LocalDateTime dateAdded;

    private LocalDateTime dateModified;

    private Long userId;  // Links to User table
}

// Category enum (inside same file for simplicity)
enum Category {
    SOCIAL_MEDIA, BANKING, EMAIL, SHOPPING, WORK, OTHER
}
