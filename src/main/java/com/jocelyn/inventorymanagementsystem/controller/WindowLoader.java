package com.jocelyn.inventorymanagementsystem.controller;

import com.jocelyn.inventorymanagementsystem.modal.Product;
import com.jocelyn.inventorymanagementsystem.modal.User;
import com.jocelyn.inventorymanagementsystem.utilities.Constant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BoxBlur;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowLoader {

    // attributes
    private final AdminDashboardController dashboardController;

    // constructor
    public WindowLoader() {
        this.dashboardController = null;
    }

    public WindowLoader(AdminDashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    // loadDashboard by role
    public void loadDashboard(String role, Button button) {
        String fxmlFile = role.equals("admin") ? Constant.ADMINDASHBOARDVIEW : Constant.USERDASHBOARDVIEW;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // loadAddWindow by fxmlPath, title, type, controllerClass
    public void loadAddWindow(String fxmlPath, String title, Constant.OperationType type, Class<?> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // get the controller
            Object controller = loader.getController();
            // set the dashboard controller and operation type
            if (controller instanceof AdminAddUpdateUserController) {
                ((AdminAddUpdateUserController) controller).setDashboardController(dashboardController);
                ((AdminAddUpdateUserController) controller).setType(type);
            } else if (controller instanceof AdminAddUpdateProductController) {
                ((AdminAddUpdateProductController) controller).setDashboardController(dashboardController);
                ((AdminAddUpdateProductController) controller).setType(type);
            }

            // create a new Stage to show the new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // set the new window's title
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);

            setBlur(true);
            stage.setOnHidden(e -> setBlur(false));
            // show the new window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // loadUpdateWindow by fxmlPath, title, type, controllerClass, selectedItem
    public void loadUpdateWindow(String fxmlPath, String title, Constant.OperationType type, Class<?> controllerClass, Object selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // get the controller
            Object controller = loader.getController();
            // set the dashboard controller and operation type
            if (controller instanceof AdminAddUpdateUserController) {
                ((AdminAddUpdateUserController) controller).setDashboardController(dashboardController);
                ((AdminAddUpdateUserController) controller).setType(type);
                ((AdminAddUpdateUserController) controller).setSelectedUser((User) selectedItem);
                ((AdminAddUpdateUserController) controller).setup();
            } else if (controller instanceof AdminAddUpdateProductController) {
                ((AdminAddUpdateProductController) controller).setDashboardController(dashboardController);
                ((AdminAddUpdateProductController) controller).setType(type);
                ((AdminAddUpdateProductController) controller).setSelectedProduct((Product) selectedItem);
                ((AdminAddUpdateProductController) controller).setup();
            }

            // create a new Stage to show the new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // set the new window's title
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);

            setBlur(true);
            stage.setOnHidden(e -> setBlur(false));
            // show the new window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // loadDeleteWindow by fxmlPath, title, category, selectedItem
    public void loadDeleteWindow(String fxmlPath, String title, Constant.Category category, Object selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // get the controller
            AdminDeleteController deleteController = loader.getController();
            deleteController.setDashboardController(dashboardController);
            // set the selected item
            if (category == Constant.Category.USER) {
                deleteController.setSelectedUser((User) selectedItem);
            } else {
                deleteController.setSelectedProduct((Product) selectedItem);
            }
            deleteController.category = category;

            // create a new Stage to show the new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // set the new window's title
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);

            setBlur(true);
            stage.setOnHidden(e -> setBlur(false));
            // show the new window
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // showAlert by message, fxmlPath
    public void showAlert(String message, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // get the controller
            AlertController alertController = loader.getController();
            alertController.setAlertMessage(message);

            // create a new Stage to show the new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            alertController.setDialogStage(stage);
            alertController.setAlertMessage(message);

            // set the new window modality and show it
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // loadScene by stage, fxmlPath
    public void loadScene(Stage stage, String fxmlPath) {
        try {
            // load the new scene
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            // set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // set the blur effect
    public void setBlur(boolean isBlur) {
        if (dashboardController != null && dashboardController.getUsername() != null) {
            if (isBlur) {
                BoxBlur blur = new BoxBlur(3, 3, 3);
                dashboardController.getUsername().getScene().getRoot().setEffect(blur);
            } else {
                dashboardController.getUsername().getScene().getRoot().setEffect(null);
            }
        }
    }

}
