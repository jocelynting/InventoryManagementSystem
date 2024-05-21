package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.Product;
import com.jocelyn.inventorymanagementsystem.modal.ProductManager;
import com.jocelyn.inventorymanagementsystem.utilities.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminAddUpdateProductController {

    // attributes
    @FXML
    private Label title;
    @FXML
    private TextField nameTF;
    @FXML
    private TextArea descriptionTA;
    @FXML
    private TextField priceTF;
    @FXML
    private TextField quantityTF;
    @FXML
    private Label warning;
    @FXML
    private Button submitBtn;
    @FXML
    private Button cancelBtn;
    private ProductManager productManager;
    private Product selectedProduct;
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

    // set selected product
    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    // initialize the controller
    public void initialize() {
        warning.setText("");
        productManager = new ProductManager();
    }

    // set up the view
    public void setup() {
        // set title and button text based on operation type
        if (type == Constant.OperationType.UPDATE) {
            title.setText("Update Product");
            submitBtn.setText("Update");
            fillUpdateInfo(selectedProduct);
        } else {
            title.setText("Add Product");
            submitBtn.setText("Add");
        }
    }

    // fill update information
    private void fillUpdateInfo(Product selectedProduct) {
        nameTF.setText(selectedProduct.getName());
        descriptionTA.setText(selectedProduct.getDescription());
        priceTF.setText(String.valueOf(selectedProduct.getPrice()));
        quantityTF.setText(String.valueOf(selectedProduct.getQuantity()));
    }

    // submit the add or update product
    @FXML
    void submit(ActionEvent event) {
        // get the product information like name, description, price, and quantity
        String name = nameTF.getText();
        String description = descriptionTA.getText();
        double price = 0;
        try {
            price = Double.parseDouble(priceTF.getText());
        } catch (NumberFormatException e) {
            warning.setText("Please enter a valid price");
            return;
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityTF.getText());
        } catch (NumberFormatException e) {
            warning.setText("Please enter a valid quantity");
            return;
        }

        // check if the name, description, price, and quantity are empty
        if (name.isEmpty() || description.isEmpty() || price == 0 || quantity == 0) {
            warning.setText("Please fill in all fields");
            return;
        }

        // add or update the product based on the operation type
        if (type == Constant.OperationType.ADD) {
            if (productManager.productExists(name)) {
                warning.setText("Product already exists");
                return;
            } else {
                warning.setText("");
                productManager.addProduct(name, description, price, quantity);
                dashboardController.refreshProductTable();
                productManager.recordProductOperation(Constant.currentUser, "Added a new product: " + name);
            }
        } else {
            productManager.updateProduct(selectedProduct.getPid(), name, description, price, quantity);
            selectedProduct.setName(name);
            selectedProduct.setDescription(description);
            selectedProduct.setPrice(price);
            selectedProduct.setQuantity(quantity);
            dashboardController.refreshProductTable();
            productManager.recordProductOperation(Constant.currentUser, "Update " + name + " information.");
        }

        Stage stage = (Stage) submitBtn.getScene().getWindow();
        stage.close();
    }

    // cancel the add or update product
    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
