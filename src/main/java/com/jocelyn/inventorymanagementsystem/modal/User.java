package com.jocelyn.inventorymanagementsystem.modal;

public class User {

    // attributes
    private int uid;
    private String username;
    private String password;
    private String role;

    // constructor
    public User(int id, String username, String password, String role) {
        this.uid = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // getters and setters
    public int getUid() {
        return uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
