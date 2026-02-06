package com.revpm.model;

public class SecurityQuestion {

    private int id;
    private int userId;
    private String question;
    private String hashedAnswer;

    public SecurityQuestion() {}

    public SecurityQuestion(int userId, String question, String hashedAnswer) {
        this.userId = userId;
        this.question = question;
        this.hashedAnswer = hashedAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getHashedAnswer() {
        return hashedAnswer;
    }

    public void setHashedAnswer(String hashedAnswer) {
        this.hashedAnswer = hashedAnswer;
    }
}
