package com.example.dodast.Controller;

import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Util.AdvertisementCard;
import com.example.dodast.Util.SceneManager;
import com.example.dodast.Util.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label usernameLabel;

    @FXML
    private FlowPane advertisementsPane;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Label messageLabel;

    private final AdvertisementService advertisementService = new AdvertisementService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usernameLabel.setText(SessionManager.getUsername());

        loadAdvertisements();
    }

    private void loadAdvertisements() {

        setLoading(true);
        advertisementsPane.getChildren().clear();
        hideError();

        try {
            List<AdvertisementResponse> advertisements = advertisementService.getActiveAdvertisements();

            if (advertisements.isEmpty()) {
                showMessage("در حال حاضر آگهی فعالی وجود ندارد");
                return;
            }

            for (AdvertisementResponse advertisement : advertisements) {

                advertisementsPane.getChildren().add(AdvertisementCard.createAdvertisementCard(advertisement));
            }

        } catch (Exception e) {
            showMessage(e.getMessage());

        } finally {
            setLoading(false);
        }
    }

    @FXML
    private void logout() {

        SessionManager.clearSession();

        try {
            Stage stage = (Stage) usernameLabel.getScene().getWindow();

            SceneManager.switchScene(stage, "login.fxml");

        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    private void setLoading(boolean loading) {
        loadingIndicator.setManaged(loading);
        loadingIndicator.setVisible(loading);
    }

    private void showMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setManaged(true);
        messageLabel.setVisible(true);
    }

    private void hideError() {
        messageLabel.setManaged(false);
        messageLabel.setVisible(false);
    }

    
}
