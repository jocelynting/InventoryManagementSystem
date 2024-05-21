package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.Activity;
import com.jocelyn.inventorymanagementsystem.modal.ActivityManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.List;

public class ActivityTable {

    // attributes
    private TableView<Activity> tableView;
    private ActivityManager activityManager;
    private ObservableList<Activity> activities = FXCollections.observableArrayList();

    // constructor
    public ActivityTable(TableView<Activity> tableView) {
        this.tableView = tableView;
        this.activityManager = new ActivityManager();
        setup();
    }

    // set up the table
    private void setup() {
        // create columns
        TableColumn<Activity, String> usernameColumn = new TableColumn<>("Name");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Activity, String> timestampColumn = new TableColumn<>("Timestamp");
        timestampColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFormattedTimestamp()));

        TableColumn<Activity, String> activityColumn = new TableColumn<>("Activity");
        activityColumn.setCellValueFactory(new PropertyValueFactory<>("activity"));

        // set the columns
        tableView.getColumns().setAll(usernameColumn, timestampColumn, activityColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setItems(activities);
        refresh();
    }

    // search activities
    public void searchActivities(Date fromDate, Date toDate) {
        List<Activity> activityList = activityManager.getActivitiesInRange(fromDate, toDate);
        activities.setAll(activityList);
    }

    // refresh the table
    public void refresh() {
        List<Activity> activityList = activityManager.getAllActivities();
        activities.setAll(activityList);
    }


}