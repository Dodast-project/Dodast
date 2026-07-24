package com.example.dodast.Controller;

import com.example.dodast.DTO.Admin.AdminDashboardResponse;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Service.AdminDashboardService;
import com.example.dodast.Util.SceneManager;
import com.example.dodast.Util.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class AdminDashboardController{

    @FXML
    private Label userCountLabel;

    @FXML
    private Label advertisementCountLabel;

    @FXML
    private Label pendingCountLabel;

    @FXML
    private Label messageLabel;

    private final AdminDashboardService adminDashboardService = new AdminDashboardService();

    @FXML
    private void initialize() {
        loadStatistics();
    }

    private void loadStatistics() {

        try {
            AdminDashboardResponse statistics = adminDashboardService.getStatistics();

            userCountLabel.setText(String.valueOf(statistics.getUserCount()));
            advertisementCountLabel.setText(String.valueOf(statistics.getAdvertisementCount()));
            pendingCountLabel.setText(String.valueOf(statistics.getPendingAdvertisementCount()));

        } catch (Exception e) {
            ShowAlert.showError("در دریافت آمار داشبورد مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void openPendingAdvertisements() {
        try {
            Stage stage = (Stage) userCountLabel.getScene().getWindow();
            SceneManager.switchScene(stage, "admin-pending-advertisements.fxml");

        } catch (Exception e) {
            ShowAlert.showError("در باز کردن آگهی‌های در انتظار مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            SessionManager.clearSession();
            Stage stage = (Stage) userCountLabel.getScene().getWindow();
            SceneManager.switchScene(stage, "login.fxml");

        } catch (Exception e) {
            ShowAlert.showError("در خروج مشکلی پیش آمد");
            e.printStackTrace();
        }
    }
}