package com.example.dodast.Controller;

import com.example.dodast.DTO.Auth.RegisterRequest;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Service.AuthService;
import com.example.dodast.Util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button registerButton;

    private final AuthService authService = new AuthService();

    @FXML
    private void register() {

        hideMessage();

        String fullName = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        String validationError = validate(fullName,
                username,
                email,
                password,
                confirmPassword
            );

        if (validationError != null) {
            ShowAlert.showError(validationError);
            return;
        }

        try {
            registerButton.setDisable(true);

            RegisterRequest request = new RegisterRequest(fullName,
                            username,
                            email,
                            password
                        );

            authService.register(request);

            SceneManager.switchScene(currentStage(), "home.fxml");

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());

        } finally {
            registerButton.setDisable(false);
        }
    }

    @FXML
    private void goToLogin() {
        try {
            SceneManager.switchScene(currentStage(), "login.fxml");
        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
        }
    }

    private String validate(String fullName,
            String username,
            String email,
            String password,
            String confirmPassword) {

        if (fullName.isBlank() ||
                username.isBlank() ||
                email.isBlank() ||
                password.isBlank()) {

            return "پر کردن تمام قسمت ها الزامی است";
        }

        if (!email.contains("@")) {
            return "ایمیل معتبر نیست";
        }

        if (password.length() < 6) {
            return "رمز عبور باید حداقل ۶ کاراکتر باشد";
        }

        if (!password.equals(confirmPassword)) {
            return "رمز عبور و تکرار آن یکسان نیست";
        }

        return null;
    }

    private Stage currentStage() {
        return (Stage) usernameField.getScene().getWindow();
    }

    private void hideMessage() {
        errorLabel.setManaged(false);
        errorLabel.setVisible(false);
    }
}