package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.User;
import com.jocelyn.inventorymanagementsystem.modal.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UserTable {

    // attributes
    private final TableView<User> tableView;
    private final UserManager userManager;
    private final ObservableList<User> users = FXCollections.observableArrayList();


    // constructor
    public UserTable(TableView<User> tableView) {
        this.tableView = tableView;
        this.userManager = new UserManager();
        setup();
    }

    // set up the table
    private void setup() {
        // create columns
        TableColumn<User, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("uid"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // set the columns
        tableView.getColumns().setAll(idColumn, nameColumn, passwordColumn, roleColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setItems(users);
        refresh();
    }

    // search users
    public void searchUsers(String search) {
        List<User> newUsers = userManager.searchUsers(search);
        users.setAll(newUsers);
    }

    // refresh the table
    public void refresh() {
        users.clear();
        users.addAll(userManager.getUsers());
    }

}
