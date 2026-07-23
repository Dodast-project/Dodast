package com.example.dodast.Controller;

import com.example.dodast.DTO.Auth.LoginRequest;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Service.AuthService;
import com.example.dodast.Util.SceneManager;
import com.example.dodast.Util.SessionManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField identifierField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    private final AuthService authService = new AuthService();

    @FXML
    private void login() {

        String identifier = identifierField.getText().trim();
        String password = passwordField.getText();

        if (identifier.isBlank() || password.isBlank()) {
            ShowAlert.showError("وارد  کردن نام کاربری و رمز عبور الزامی است");
            return;
        }

        try {
            loginButton.setDisable(true);

            LoginRequest request = new LoginRequest(identifier, password);

            authService.login(request);

            if(SessionManager.isAdmin()) {
                Stage stage = currentStage();
                SceneManager.switchScene(stage, "admin-dashboard.fxml");
            } else {
                Stage stage = currentStage();
                SceneManager.switchScene(stage, "home.fxml");
            }

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();

        } finally {
            loginButton.setDisable(false);
        }
    }

    @FXML
    private void goToRegister() {
        try {
            SceneManager.switchScene(currentStage(),"register.fxml");
        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
        }
    }

    private Stage currentStage() {
        return (Stage) identifierField.getScene().getWindow();
    }

}