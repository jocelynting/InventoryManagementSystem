package com.jocelyn.inventorymanagementsystem.modal;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Activity {

    // attributes
    private int uid;
    private String username;
    private String activity;
    private Timestamp timestamp;

    // constructor
    public Activity(int uid, String username, String activity, Timestamp timestamp) {
        this.uid = uid;
        this.username = username;
        this.activity = activity;
        this.timestamp = timestamp;
    }

    // getters and setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    // get formatted timestamp
    public String getFormattedTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }
}