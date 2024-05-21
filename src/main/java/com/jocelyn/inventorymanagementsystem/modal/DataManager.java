package com.jocelyn.inventorymanagementsystem.modal;

import java.sql.*;

public class DataManager {

    // attributes for database connection
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    // get connection
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // execute query
    protected ResultSet executeQuery(String query) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    // execute update
    protected int executeUpdate(String query) throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        int result = stmt.executeUpdate(query);
        closeConnection(conn);
        return result;
    }

    // close connection
    protected void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // record activity
    protected void recordActivity(User user, String activity) {
        Connection conn = null;
        try {
            conn = getConnection();
            String sql = "INSERT INTO user_activity (user_id, activity) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getUid());
            stmt.setString(2, activity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }
}
