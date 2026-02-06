package com.revpm.main;

import com.revpm.model.PasswordEntry;
import com.revpm.model.User;
import com.revpm.service.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);

    private static final UserService userService = new UserService();
    private static final PasswordService passwordService = new PasswordService();
    private static final SecurityQuestionService sqService = new SecurityQuestionService();
    private static final OTPService otpService = new OTPService();

    private static User loggedInUser = null;

    public static void main(String[] args) {
        showMainMenu();
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== Password Manager ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> registerUser();
                case "2" -> loginUser();
                case "3" -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        boolean success = userService.registerUser(username, email, password);
        if (success) {
            System.out.println("Registration successful! You can now log in.");
        } else {
            System.out.println("Registration failed! Username might already exist.");
        }
    }

    private static void loginUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        boolean authenticated = userService.authenticateUser(username, password);
        if (authenticated) {
            Optional<User> userOpt = userService.getUserByUsername(username);
            userOpt.ifPresent(user -> loggedInUser = user);
            System.out.println("Login successful! Welcome, " + loggedInUser.getUsername());
            showUserMenu();
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    private static void showUserMenu() {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Add Password Entry");
            System.out.println("2. View Password Entries");
            System.out.println("3. Logout");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addPasswordEntry();
                case "2" -> viewPasswordEntries();
                case "3" -> {
                    loggedInUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addPasswordEntry() {
        System.out.print("Title/Account Name: ");
        String title = scanner.nextLine().trim();

        System.out.print("Username for account: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password for account: ");
        String password = scanner.nextLine().trim();

        boolean success = passwordService.addPasswordEntry(loggedInUser.getId(), title, username, password);
        if (success) {
            System.out.println("Password entry added successfully!");
        } else {
            System.out.println("Failed to add password entry.");
        }
    }

    private static void viewPasswordEntries() {
        List<PasswordEntry> entries = passwordService.getPasswordEntries(loggedInUser.getId());
        if (entries.isEmpty()) {
            System.out.println("No password entries found.");
            return;
        }

        System.out.println("\n--- Your Password Entries ---");
        for (PasswordEntry entry : entries) {
            String decrypted = passwordService.decryptPassword(entry.getEncryptedPassword());
            System.out.println("Title: " + entry.getTitle());
            System.out.println("Username: " + entry.getUsername());
            System.out.println("Password: " + decrypted);
            System.out.println("---------------------------");
        }
    }
}
