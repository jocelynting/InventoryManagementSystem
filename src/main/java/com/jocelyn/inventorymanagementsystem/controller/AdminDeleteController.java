package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.Product;
import com.jocelyn.inventorymanagementsystem.modal.ProductManager;
import com.jocelyn.inventorymanagementsystem.modal.User;
import com.jocelyn.inventorymanagementsystem.modal.UserManager;
import com.jocelyn.inventorymanagementsystem.utilities.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminDeleteController {

    // attributes
    @FXML
    private Label alertTitle;
    @FXML
    private Label alertMessage;
    @FXML
    private Button submitBtn;
    @FXML
    private Button cancelBtn;
    public Constant.Category category = Constant.Category.USER;
    private AdminDashboardController dashboardController;
    private UserManager userManager;
    private User selectedUser;
    private ProductManager productManager;
    private Product selectedProduct;

    // initialize the controller
    public void initialize() {
        userManager = new UserManager();
        productManager = new ProductManager();
    }

    // set dashboard controller
    public void setDashboardController(AdminDashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    // set selected user and alert message
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        alertTitle.setText("Delete User");
        alertMessage.setText("Are you sure you want to delete the user: " + selectedUser.getUsername() + "?");
    }

    // set selected product and alert message
    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
        alertTitle.setText("Delete Product");
        alertMessage.setText("Are you sure you want to delete the product: " + selectedProduct.getName() + "?");
    }

    // delete the user or product
    @FXML
    void delete(ActionEvent event) {

        switch (category) {
            case USER:
                deleteUser();
                break;
            case PRODUCT:
                deleteProduct();
                break;
        }

        Stage stage = (Stage) submitBtn.getScene().getWindow();
        stage.close();
    }

    // delete the user
    private void deleteUser() {
        userManager.deleteUser(selectedUser.getUid());
        dashboardController.refreshUserTable();
        userManager.recordUserOperation(Constant.currentUser, "Delete " + selectedUser.getUsername());
    }

    // delete the product
    private void deleteProduct() {
        productManager.deleteProduct(selectedProduct.getPid());
        dashboardController.refreshProductTable();
        productManager.recordProductOperation(Constant.currentUser, "Delete " + selectedProduct.getName());
    }

    // cancel the delete
    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
