package com.jocelyn.inventorymanagementsystem.modal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityManager extends DataManager {

    // get all user activities from the database
    public List<Activity> getAllActivities() {
        List<Activity> activities = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            String sql = "SELECT activity.user_id, users.username, activity.activity, activity.timestamp " +
                    "FROM user_activity activity " +
                    "JOIN users ON activity.user_id = users.id " +
                    "ORDER BY activity.timestamp DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int uid = rs.getInt("user_id");
                String username = rs.getString("username");
                String activity = rs.getString("activity");
                Timestamp timestamp = rs.getTimestamp("timestamp");
                activities.add(new Activity(uid, username, activity, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return activities;
    }

    // get user activities in a given range from the database
    public List<Activity> getActivitiesInRange(Date fromDate, Date toDate) {
        List<Activity> activities = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            String sql = "SELECT activity.user_id, users.username, activity.activity, activity.timestamp " +
                    "FROM user_activity activity " +
                    "JOIN users ON activity.user_id = users.id " +
                    "WHERE activity.timestamp BETWEEN ? AND TIMESTAMP(?, '23:59:59')  " +
                    "ORDER BY activity.timestamp DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, fromDate);
            stmt.setDate(2, toDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int uid = rs.getInt("user_id");
                String username = rs.getString("username");
                String activity = rs.getString("activity");
                Timestamp timestamp = rs.getTimestamp("timestamp");
                activities.add(new Activity(uid, username, activity, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return activities;
    }
}