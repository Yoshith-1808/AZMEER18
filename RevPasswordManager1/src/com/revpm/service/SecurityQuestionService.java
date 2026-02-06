package com.revpm.service;

import com.revpm.dao.SecurityQuestionDAO;
import com.revpm.model.SecurityQuestion;
import com.revpm.util.HashUtil;

import java.util.List;

public class SecurityQuestionService {

    private final SecurityQuestionDAO sqDAO = new SecurityQuestionDAO();

    // Add security question
    public boolean addSecurityQuestion(int userId, String question, String answer) {
        String hashedAnswer = HashUtil.hashPassword(answer);
        SecurityQuestion sq = new SecurityQuestion(userId, question, hashedAnswer);
        return sqDAO.addQuestion(sq);
    }

    // Get all questions for a user
    public List<SecurityQuestion> getSecurityQuestions(int userId) {
        return sqDAO.getQuestionsByUserId(userId);
    }

    // Verify answer
    public boolean verifyAnswer(SecurityQuestion sq, String answer) {
        String hashedInput = HashUtil.hashPassword(answer);
        return hashedInput.equals(sq.getHashedAnswer());
    }
}
