package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.Product;
import com.jocelyn.inventorymanagementsystem.modal.ProductManager;
import com.jocelyn.inventorymanagementsystem.modal.UserManager;
import com.jocelyn.inventorymanagementsystem.utilities.Constant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UserDashboardController {

    // attributes
    @FXML
    private Label username;
    @FXML
    private Button inventoryBtn;
    @FXML
    private TextField searchTF;
    @FXML
    private Button signOutBtn;
    @FXML
    private TableView<Product> productTableView;
    private ProductTable productTable;

    // initialize the dashboard
    public void initialize() {
        username.setText(Constant.currentUser.getUsername());
        productTable = new ProductTable(productTableView);
    }

    // open inventory management
    @FXML
    void openInventoryManagement(ActionEvent event) {
        productTableView.setVisible(true);
    }

    // search products
    @FXML
    void search(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String searchText = searchTF.getText();
            productTable.searchProducts(searchText);
        }
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
