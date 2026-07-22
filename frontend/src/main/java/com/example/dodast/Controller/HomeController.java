package com.example.dodast.Controller;

import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Util.AdvertisementCard;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.SceneManager;
import com.example.dodast.Util.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
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

    private void showAdvertisementDetail(Long advertisementId) {
        try {
            AdvertisementSession.setSelectedAdvertisementId(advertisementId);

            Stage stage = (Stage) advertisementsPane.getScene().getWindow();

            SceneManager.switchScene(stage, "advertisement-detail.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
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

                advertisementsPane.getChildren().add(AdvertisementCard.createAdvertisementCard(advertisement, () -> showAdvertisementDetail(advertisement.getId())));
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

    @FXML
    private void showFavorites() {
        try {
            Stage stage = (Stage) advertisementsPane.getScene().getWindow();
            SceneManager.switchScene(stage, "favorites.fxml");
        } catch (Exception e) {
            showMessage("خطایی در نشان دادن علاقه‌مندی ها پیش آمد");
            e.printStackTrace();
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

    @FXML
    private void openCreateAdvertisement() {

        try {
            Stage stage = (Stage) usernameLabel.getScene().getWindow();

            SceneManager.switchScene(stage, "create-advertisement.fxml");

        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    
}
