package com.jocelyn.inventorymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertController {

    // attributes
    @FXML
    private Label alertMessage;
    @FXML
    private Button OKBtn;
    private Stage dialogStage;

    // set dialog stage
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // set alert message
    public void setAlertMessage(String message) {
        alertMessage.setText(message);
    }

    // close the alert
    @FXML
    void confirm(ActionEvent event) {
        Stage stage = (Stage) OKBtn.getScene().getWindow();
        stage.close();
    }
}
