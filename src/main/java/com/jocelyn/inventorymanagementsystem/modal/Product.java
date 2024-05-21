package com.jocelyn.inventorymanagementsystem.modal;

public class Product {

    // attributes
    private int pid;
    private String name;
    private String description;
    private double price;
    private int quantity;

    // constructor
    public Product(int pid, String name, String description, double price, int quantity) {
        this.pid = pid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // getters and setters
    public int getPid() {
        return pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
