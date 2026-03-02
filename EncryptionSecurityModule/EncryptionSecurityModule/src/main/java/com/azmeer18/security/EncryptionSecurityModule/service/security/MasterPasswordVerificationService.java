package com.azmeer18.security.encryptionsecuritymodule.service.security;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
public class MasterPasswordVerificationService {

    // Hash a master password
    public String hashMasterPassword(String masterPassword) {
        return BCrypt.hashpw(masterPassword, BCrypt.gensalt());
    }

    // Verify a master password against stored hash
    public boolean verifyMasterPassword(String candidatePassword, String storedHash) {
        return BCrypt.checkpw(candidatePassword, storedHash);
    }
}