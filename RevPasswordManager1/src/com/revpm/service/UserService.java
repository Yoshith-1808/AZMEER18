package com.revpm.service;

import com.revpm.dao.UserDAO;
import com.revpm.model.User;
import com.revpm.util.HashUtil;

import java.util.Optional;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    // Register new user
    public boolean registerUser(String username, String email, String password) {
        String hashed = HashUtil.hashPassword(password);
        User user = new User(username, email, hashed);
        return userDAO.addUser(user);
    }

    // Authenticate user login
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOpt = userDAO.getUserByUsername(username);
        if (userOpt.isPresent()) {
            String hashedInput = HashUtil.hashPassword(password);
            return hashedInput.equals(userOpt.get().getHashedPassword());
        }
        return false;
    }

    // Get user by username
    public Optional<User> getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}