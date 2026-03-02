package com.azmeer18.security.encryptionsecuritymodule.service.security;

import org.springframework.stereotype.Service;

@Service
public class PasswordStrengthService {

    // Evaluate password strength
    public String evaluatePasswordStrength(String password) {
        int score = 0;

        if (password.length() >= 8)
            score++;
        if (password.matches(".*[A-Z].*"))
            score++;
        if (password.matches(".*[a-z].*"))
            score++;
        if (password.matches(".*[0-9].*"))
            score++;
        if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*"))
            score++;

        switch (score) {
            case 5:
                return "Very Strong";
            case 4:
                return "Strong";
            case 3:
                return "Medium";
            default:
                return "Weak";
        }
    }
}