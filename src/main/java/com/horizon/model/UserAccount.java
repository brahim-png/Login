package com.horizon.model;

public class UserAccount {
    private String username;
    private String password;

    // Constructor
    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to check user credentials
    public boolean isValidUser() {
        // For simplicity, using hard-coded credentials. You can later connect this with a database or a config file.
        return "admin".equals(this.username) && "password123".equals(this.password);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                '}';
    }
}
