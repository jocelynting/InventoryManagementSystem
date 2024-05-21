package com.jocelyn.inventorymanagementsystem.modal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager extends DataManager {

    // authenticate a user with the provided credentials and return the user object
    public User authenticateUser(String username, String password) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");
                return new User(id, username, password, role);
            } // Returns true if a user with the provided credentials is found
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return null;
    }

    // get all users from the database
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                users.add(new User(id, username, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return users;
    }

    // get user role
    public String getUserRole(String username) {
        String role = null;
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT role FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role = rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return role;
    }

    // check if a user with the provided username exists
    public boolean userExists(String username) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a user with the provided username is found
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return false;
    }

    // add a user to the database
    public void addUser(String username, String password, String role) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    // update a user in the database
    public void updateUser(int id, String username, String password, String role) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    // search users in the database
    public List<User> searchUsers(String searchText) {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM users WHERE username LIKE ? OR role LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + searchText + "%");
            stmt.setString(2, "%" + searchText + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                users.add(new User(id, username, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return users;
    }

    // delete a user from the database
    public void deleteUser(int id) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    // record login activity
    public void recordLogin(User user) {
        recordActivity(user, "Login");
    }

    // record logout activity
    public void recordLogout(User user) {
        recordActivity(user, "Logout");
    }

    // record user operation activity such as add, update, delete
    public void recordUserOperation(User user, String activity) {
        recordActivity(user, activity);
    }

}
