package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.User;
import com.jocelyn.inventorymanagementsystem.modal.UserManager;
import com.jocelyn.inventorymanagementsystem.utilities.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    // attributes
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Button loginBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label warning;
    private final UserManager userManager = new UserManager();

    // initialize the login view
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warning.setVisible(false);
    }

    // login
    @FXML
    void login(ActionEvent event) {
        loginToDashboard();
    }

    // login by key pressed
    @FXML
    void loginByKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginToDashboard();
        }
    }

    // login to dashboard
    private void loginToDashboard() {
        String username = usernameTF.getText();
        String password = passwordTF.getText();

        // check if username and password are empty
        if (username.isEmpty()) {
            warning.setText("Please enter username");
            warning.setVisible(true);
        } else if (password.isEmpty()) {
            warning.setText("Please enter password");
            warning.setVisible(true);
        } else {
            // authenticate the user
            User currentUser = userManager.authenticateUser(username, password);
            if (currentUser != null) {
                Constant.currentUser = currentUser;
                String role = userManager.getUserRole(username);
                WindowLoader windowLoader = new WindowLoader();
                windowLoader.loadDashboard(role, loginBtn);
                userManager.recordLogin(currentUser);
            } else {
                warning.setText("Invalid username or password");
                warning.setVisible(true);
            }
        }
    }

    // cancel the login
    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}