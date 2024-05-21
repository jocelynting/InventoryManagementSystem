package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.User;
import com.jocelyn.inventorymanagementsystem.modal.UserManager;
import com.jocelyn.inventorymanagementsystem.utilities.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AdminAddUpdateUserController {

    // attributes
    @FXML
    private Label title;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private MenuButton roleSelection;
    @FXML
    private MenuItem adminRole;
    @FXML
    private MenuItem userRole;
    @FXML
    private Label warning;
    @FXML
    private Button submitBtn;
    @FXML
    private Button cancelBtn;
    private UserManager userManager;
    private User selectedUser;
    private AdminDashboardController dashboardController;
    private Constant.OperationType type = Constant.OperationType.ADD;

    // set dashboard controller
    public void setDashboardController(AdminDashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    // set operation type
    public void setType(Constant.OperationType type) {
        this.type = type;
    }

    // set selected user
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    // initialize the controller
    public void initialize() {
        warning.setText("");
        userManager = new UserManager();
    }

    // setup the view
    public void setup() {
        // set title and button text based on operation type
        if (type == Constant.OperationType.UPDATE) {
            title.setText("Update User");
            submitBtn.setText("Update");
            fillUpdateInfo(selectedUser);
        } else {
            title.setText("Add New User");
            submitBtn.setText("Add");
        }
    }

    // fill the update information
    public void fillUpdateInfo(User user) {
        nameTF.setText(user.getUsername());
        passwordTF.setText(user.getPassword());
        roleSelection.setText(user.getRole());
    }

    // select role
    @FXML
    void selectRole(ActionEvent event) {
        if (event.getSource() == adminRole) {
            roleSelection.setText("admin");
        } else if (event.getSource() == userRole) {
            roleSelection.setText("user");
        }
    }

    // submit the add or update action
    @FXML
    void submit(ActionEvent event) {
        // check if the name, password and role are empty
        if (nameTF.getText().isEmpty()) {
            warning.setText("Name cannot be empty");
            return;
        } else if (passwordTF.getText().isEmpty()) {
            warning.setText("Password cannot be empty");
            return;
        } else if (roleSelection.getText().equals("Select Role")) {
            warning.setText("Please select a role");
            return;
        } else {
            // add or update the user based on the operation type
            if (type == Constant.OperationType.ADD) {
                if (userManager.userExists(nameTF.getText())) {
                    warning.setText("User already exists");
                    return;
                } else {
                    warning.setText("");
                    userManager.addUser(nameTF.getText(), passwordTF.getText(), roleSelection.getText());
                    dashboardController.refreshUserTable();
                    userManager.recordUserOperation(Constant.currentUser, "Added a new user: " + nameTF.getText());
                }
            } else {
                userManager.updateUser(selectedUser.getUid(), nameTF.getText(), passwordTF.getText(), roleSelection.getText());
                selectedUser.setUsername(nameTF.getText());
                selectedUser.setPassword(passwordTF.getText());
                selectedUser.setRole(roleSelection.getText());
                dashboardController.refreshUserTable();
                userManager.recordUserOperation(Constant.currentUser, "Update " + nameTF.getText() + " information.");
            }

            warning.setText("");
        }
        Stage stage = (Stage) submitBtn.getScene().getWindow();
        stage.close();
    }

    // cancel the add or update action
    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
