package com.jocelyn.inventorymanagementsystem.modal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager extends DataManager {

    // get all products from the database
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int pid = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                products.add(new Product(pid, name, description, price, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }

        return products;
    }

    // check if a product exists in the database
    public boolean productExists(String name) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM products WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return false;
    }

    // add a product to the database
    public void addProduct(String name, String description, double price, int quantity) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "INSERT INTO products (name, description, price, quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    // update a product in the database
    public void updateProduct(int pid, String name, String description, double price, int quantity) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "UPDATE products SET name = ?, description = ?, price = ?, quantity = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setInt(5, pid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    // search products in the database
    public List<Product> searchProducts(String searchText) {
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + searchText + "%");
            stmt.setString(2, "%" + searchText + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                products.add(new Product(pid, name, description, price, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }

        return products;
    }

    // delete a product from the database
    public void deleteProduct(int pid) {
        Connection conn = null;
        try {
            conn = getConnection();
            String query = "DELETE FROM products WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, pid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
    }

    // record product operation activity such as add, update, delete
    public void recordProductOperation(User user,String activity) {
        recordActivity(user, activity);
    }
}
