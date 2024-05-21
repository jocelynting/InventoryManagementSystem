package com.jocelyn.inventorymanagementsystem.utilities;

import com.jocelyn.inventorymanagementsystem.modal.User;

public class Constant {

    // paths for loading views
    public static final String LOGINVIEW = "/com/jocelyn/inventorymanagementsystem/login.fxml";
    public static final String ADMINDASHBOARDVIEW = "/com/jocelyn/inventorymanagementsystem/admin-dashboard.fxml";
    public static final String ADDUPDATEUSERVIEW = "/com/jocelyn/inventorymanagementsystem/admin-user-add-update.fxml";
    public static final String ADDUPDATEPRODUCTVIEW = "/com/jocelyn/inventorymanagementsystem/admin-product-add-update.fxml";
    public static final String ADMINDELETEVIEW = "/com/jocelyn/inventorymanagementsystem/admin-delete-alert.fxml";
    public static final String ALERTVIEW = "/com/jocelyn/inventorymanagementsystem/alert.fxml";
    public static final String USERDASHBOARDVIEW = "/com/jocelyn/inventorymanagementsystem/user-dashboard.fxml";

    // enums for user and product
    public enum Category {
        USER, PRODUCT
    }

    // enums for operation type such as add, update, delete
    public enum OperationType {
        ADD, UPDATE, DELETE
    }

    // current user
    public static User currentUser;

    // set current user
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

}
