package com.revpm.dao;

import com.revpm.model.VerificationCode;
import com.revpm.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class VerificationCodeDAO {

    // Add verification code
    public boolean addCode(VerificationCode code) {
        String sql = "INSERT INTO verification_codes (user_id, code, expiry) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, code.getUserId());
            stmt.setString(2, code.getCode());
            stmt.setTimestamp(3, Timestamp.valueOf(code.getExpirationTime()));

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get latest code for user
    public Optional<VerificationCode> getLatestCodeByUserId(int userId) {
        String sql = "SELECT * FROM verification_codes WHERE user_id = ? ORDER BY id DESC LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                VerificationCode code = new VerificationCode();
                code.setId(rs.getInt("id"));
                code.setUserId(rs.getInt("user_id"));
                code.setCode(rs.getString("code"));
                code.setExpirationTime(rs.getTimestamp("expiry").toLocalDateTime());
                return Optional.of(code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
