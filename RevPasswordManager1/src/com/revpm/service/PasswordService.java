package com.revpm.service;

import com.revpm.dao.PasswordEntryDAO;
import com.revpm.model.PasswordEntry;
import com.revpm.util.EncryptionUtil;

import java.util.List;

public class PasswordService {

    private final PasswordEntryDAO entryDAO = new PasswordEntryDAO();

    // Add new password entry
    public boolean addPasswordEntry(int userId, String title, String username, String password) {
        try {
            String encrypted = EncryptionUtil.encrypt(password);
            PasswordEntry entry = new PasswordEntry(userId, title, username, encrypted);
            return entryDAO.addEntry(entry);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all entries for user
    public List<PasswordEntry> getPasswordEntries(int userId) {
        return entryDAO.getEntriesByUserId(userId);
    }

    // Decrypt password
    public String decryptPassword(String encrypted) {
        try {
            return EncryptionUtil.decrypt(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
