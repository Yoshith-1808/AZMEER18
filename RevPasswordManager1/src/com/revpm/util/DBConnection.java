package com.revpm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Update these values according to your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/revpassword_manager";
    private static final String USER = "root"; // replace with your DB username
    private static final String PASSWORD = "Yoshith@18"; // replace with your DB password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
