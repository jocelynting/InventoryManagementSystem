package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.Activity;
import com.jocelyn.inventorymanagementsystem.modal.Product;
import com.jocelyn.inventorymanagementsystem.modal.User;
import com.jocelyn.inventorymanagementsystem.modal.UserManager;
import com.jocelyn.inventorymanagementsystem.utilities.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Date;

public class AdminDashboardController {

    // attributes
    @FXML
    private Label username;
    @FXML
    private Button usersBtn;
    @FXML
    private Button inventoryBtn;
    @FXML
    private Button activityBtn;
    @FXML
    private AnchorPane activityView;
    @FXML
    private AnchorPane managementView;
    @FXML
    private Label sectionTitle;
    @FXML
    private TextField searchTF;
    @FXML
    private Button addBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private Button searchBtn;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableView<Activity> activityTableView;
    @FXML
    private Button signOutBtn;
    private UserTable userTable;
    private ProductTable productTable;
    private ActivityTable activityTable;
    private Constant.Category category = Constant.Category.USER;

    // get username
    public Label getUsername() {
        return username;
    }

    // initialize the dashboard
    public void initialize() {
        username.setText(Constant.currentUser.getUsername());
        userTable = new UserTable(userTableView);
        productTable = new ProductTable(productTableView);
        activityTable = new ActivityTable(activityTableView);
        userTableView.setVisible(true);
        productTableView.setVisible(false);
        activityView.setVisible(false);
    }

    // refresh user table
    public void refreshUserTable() {
        userTable.refresh();
    }

    // refresh product table
    public void refreshProductTable() {
        productTable.refresh();
    }

    // open user management
    @FXML
    void openUserManagement(ActionEvent event) {
        category = Constant.Category.USER;
        userTableView.setVisible(true);
        productTableView.setVisible(false);
        managementView.setVisible(true);
        activityView.setVisible(false);
        sectionTitle.setText("User Management");
    }

    // open inventory management
    @FXML
    void openInventoryManagement(ActionEvent event) {
        category = Constant.Category.PRODUCT;
        userTableView.setVisible(false);
        productTableView.setVisible(true);
        managementView.setVisible(true);
        activityView.setVisible(false);
        sectionTitle.setText("Inventory Management");
    }

    // open activity log
    @FXML
    void openActivity(ActionEvent event) {
        managementView.setVisible(false);
        activityView.setVisible(true);
        sectionTitle.setText("Activity Log");
        activityTable.refresh();
    }

    // search users or products
    @FXML
    void search(KeyEvent event) {
        if (category == Constant.Category.USER) {
            searchUsers(event);
        } else {
            searchProducts(event);
        }
    }

    // search users
    private void searchUsers(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchText = searchTF.getText();
            userTable.searchUsers(searchText);
        }
    }

    // search products
    private void searchProducts(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchText = searchTF.getText();
            productTable.searchProducts(searchText);
        }
    }

    // add user or product
    @FXML
    void addAction(ActionEvent event) {
        // load the add window
        WindowLoader windowLoader = new WindowLoader(this);
        // check if the category is user or product to load the correct window
        if (category == Constant.Category.USER) {

            windowLoader.loadAddWindow(Constant.ADDUPDATEUSERVIEW, "Add User", Constant.OperationType.ADD, AdminAddUpdateUserController.class);
        } else {
            windowLoader.loadAddWindow(Constant.ADDUPDATEPRODUCTVIEW, "Add Product", Constant.OperationType.ADD, AdminAddUpdateProductController.class);
        }
    }

    // update user or product
    @FXML
    void updateAction(ActionEvent event) {
        WindowLoader windowLoader = new WindowLoader(this);
        // check if the category is user or product to load the correct window
        if (category == Constant.Category.USER) {
            User selectedUser = userTableView.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                windowLoader.showAlert("Please select a user to update.", Constant.ALERTVIEW);
                return;
            }
            windowLoader.loadUpdateWindow(Constant.ADDUPDATEUSERVIEW, "Update User", Constant.OperationType.UPDATE, AdminAddUpdateUserController.class, selectedUser);
        } else {
            Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
            if (selectedProduct == null) {
                windowLoader.showAlert("Please select a product to update.", Constant.ALERTVIEW);
                return;
            }
            windowLoader.loadUpdateWindow(Constant.ADDUPDATEPRODUCTVIEW, "Update Product", Constant.OperationType.UPDATE, AdminAddUpdateProductController.class, selectedProduct);
        }
    }

    // delete user or product
    @FXML
    void deleteAction(ActionEvent event) {
        WindowLoader windowLoader = new WindowLoader(this);
        // check if the category is user or product to load the correct window
        if (category == Constant.Category.USER) {
            User selectedUser = userTableView.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                windowLoader.showAlert("Please select a user to delete.", Constant.ALERTVIEW);
                return;
            }
            windowLoader.loadDeleteWindow(Constant.ADMINDELETEVIEW, "Delete User", Constant.Category.USER, selectedUser);
        } else {
            Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
            if (selectedProduct == null) {
                windowLoader.showAlert("Please select a product to delete.", Constant.ALERTVIEW);
                return;
            }
            windowLoader.loadDeleteWindow(Constant.ADMINDELETEVIEW, "Delete Product", Constant.Category.PRODUCT, selectedProduct);
        }
    }

    // search activities
    @FXML
    void searchActivity(ActionEvent event) {
        // check if both dates are selected
        if (this.fromDate.getValue() == null || this.toDate.getValue() == null) {
            // show alert if both dates are not selected
            WindowLoader windowLoader = new WindowLoader();
            windowLoader.showAlert("Please select both from and to dates.", Constant.ALERTVIEW);
            return;
        }
        // search activities by date
        Date fromDate = java.sql.Date.valueOf(this.fromDate.getValue());
        Date toDate = java.sql.Date.valueOf(this.toDate.getValue());
        activityTable.searchActivities(fromDate, toDate);
    }

    // sign out the user
    @FXML
    void signOut(ActionEvent event) {
        UserManager userManager = new UserManager();
        userManager.recordLogout(Constant.currentUser);
        Stage stage = (Stage) signOutBtn.getScene().getWindow();
        WindowLoader windowLoader = new WindowLoader();
        windowLoader.loadScene(stage, Constant.LOGINVIEW);
    }
}
