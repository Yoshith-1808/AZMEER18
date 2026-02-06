package com.revpm.dao;

import com.revpm.model.PasswordEntry;
import com.revpm.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PasswordEntryDAO {

    // Add new password entry
    public boolean addEntry(PasswordEntry entry) {
        String sql = "INSERT INTO password_entries (user_id, account_name, account_username, account_password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entry.getUserId());
            stmt.setString(2, entry.getTitle()); // account_name
            stmt.setString(3, entry.getUsername()); // account_username
            stmt.setString(4, entry.getEncryptedPassword()); // account_password

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all entries by user
    public List<PasswordEntry> getEntriesByUserId(int userId) {
        List<PasswordEntry> list = new ArrayList<>();
        String sql = "SELECT * FROM password_entries WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRowToEntry(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private PasswordEntry mapRowToEntry(ResultSet rs) throws Exception {
        PasswordEntry entry = new PasswordEntry();
        entry.setId(rs.getInt("id"));
        entry.setUserId(rs.getInt("user_id"));
        entry.setTitle(rs.getString("account_name"));
        entry.setUsername(rs.getString("account_username"));
        entry.setEncryptedPassword(rs.getString("account_password"));
        return entry;
    }
}
