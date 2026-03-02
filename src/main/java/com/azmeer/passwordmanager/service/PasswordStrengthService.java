package com.azmeer.passwordmanager.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordStrengthService {

    public String analyzeStrength(String password) {
        int length = password.length();
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        int score = 0;
        if (length >= 12) score += 2;
        else if (length >= 8) score += 1;

        if (hasUpper) score++;
        if (hasLower) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        if (score <= 3) return "Weak";
        if (score <= 5) return "Medium";
        if (score <= 7) return "Strong";
        return "Very Strong";
    }

    public boolean isWeak(String password) {
        return "Weak".equals(analyzeStrength(password));
    }
}
