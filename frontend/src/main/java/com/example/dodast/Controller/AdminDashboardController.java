package com.example.dodast.Controller;

import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private Label titleLabel;

    @FXML
    private void openPendingAdvertisements() {
        try {
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            SceneManager.switchScene(stage, "admin-pending-advertisement.fxml");

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            SceneManager.switchScene(stage, "login.fxml");
        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        }
    }
}