package com.revpm.dao;

import com.revpm.model.SecurityQuestion;
import com.revpm.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SecurityQuestionDAO {

    // Add security question
    public boolean addQuestion(SecurityQuestion sq) {
        String sql = "INSERT INTO security_questions (user_id, question, answer) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sq.getUserId());
            stmt.setString(2, sq.getQuestion());
            stmt.setString(3, sq.getHashedAnswer());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all questions by user
    public List<SecurityQuestion> getQuestionsByUserId(int userId) {
        List<SecurityQuestion> list = new ArrayList<>();
        String sql = "SELECT * FROM security_questions WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SecurityQuestion sq = new SecurityQuestion();
                sq.setId(rs.getInt("id"));
                sq.setUserId(rs.getInt("user_id"));
                sq.setQuestion(rs.getString("question"));
                sq.setHashedAnswer(rs.getString("answer"));
                list.add(sq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
