package com.revpm.model;

import java.time.LocalDateTime;

public class VerificationCode {

    private int id;
    private int userId;
    private String code;
    private LocalDateTime expirationTime; // corresponds to expiry

    public VerificationCode() {}

    public VerificationCode(int userId, String code, LocalDateTime expirationTime) {
        this.userId = userId;
        this.code = code;
        this.expirationTime = expirationTime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
