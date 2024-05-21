package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.Product;
import com.jocelyn.inventorymanagementsystem.modal.ProductManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ProductTable {

    // attributes
    private final TableView<Product> tableView;
    private final ProductManager productManager;
    private final ObservableList<Product> products = FXCollections.observableArrayList();

    // constructor
    public ProductTable(TableView<Product> tableView) {
        this.tableView = tableView;
        this.productManager = new ProductManager();
        setup();
    }

    // set up the table
    private void setup() {
        // create columns
        TableColumn<Product, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Product, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // set the columns
        tableView.getColumns().setAll(idColumn, nameColumn, descriptionColumn, priceColumn, quantityColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setItems(products);
        refresh();
    }

    // search products
    public void searchProducts(String search) {
        List<Product> newProducts = productManager.searchProducts(search);
        products.setAll(newProducts);
    }

    // refresh the table
    public void refresh() {
        products.clear();
        products.addAll(productManager.getProducts());
    }

}
