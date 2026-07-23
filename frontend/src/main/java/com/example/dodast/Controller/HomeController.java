package com.example.dodast.Controller;

import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Util.AdvertisementCard;
import com.example.dodast.Util.AdvertisementFormSession;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.NavigationSession;
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
    private Label messageLabel;

    private final AdvertisementService advertisementService = new AdvertisementService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usernameLabel.setText(SessionManager.getUsername());

        loadAdvertisements();
    }

    private void showAdvertisementDetail(Long advertisementId) {
        try {
            NavigationSession.setPreviousPage("home.fxml");
            AdvertisementSession.setSelectedAdvertisementId(advertisementId);

            Stage stage = (Stage) advertisementsPane.getScene().getWindow();

            SceneManager.switchScene(stage, "advertisement-detail.fxml");

        } catch (Exception e) {
            ShowAlert.showError("خطایی در نشان دادن آگهی پیش آمد");
            e.printStackTrace();
        }
    }

    private void loadAdvertisements() {

        advertisementsPane.getChildren().clear();
        hideMessage();

        try {
            
            List<AdvertisementResponse> advertisements = advertisementService.getActiveAdvertisements();

            if (advertisements.isEmpty()) {
                showMessage("در حال حاضر آگهی فعالی وجود ندارد");
                return;
            }

            for (AdvertisementResponse advertisement : advertisements) {

                AdvertisementCard advertisementCard = new AdvertisementCard(advertisement, () -> showAdvertisementDetail(advertisement.getId()));
                advertisementsPane.getChildren().add(advertisementCard.getView());
                advertisementCard.setShowStatus(false);
                advertisementCard.setShowManagementButtons(false);
            }

        } catch (Exception e) {
            ShowAlert.showError("در بارگذاری آگهی مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {

        SessionManager.clearSession();

        try {
            Stage stage = (Stage) usernameLabel.getScene().getWindow();

            SceneManager.switchScene(stage, "login.fxml");

        } catch (Exception e) {
            ShowAlert.showError("در خروج مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void showFavorites() {
        try {
            Stage stage = (Stage) advertisementsPane.getScene().getWindow();
            SceneManager.switchScene(stage, "favorites.fxml");
        } catch (Exception e) {
            ShowAlert.showError("خطایی در نشان دادن علاقه‌مندی ها پیش آمد");
            e.printStackTrace();
        }
        
    }

    @FXML
    private void openMyAdvertisements() {
        try {
            Stage stage = (Stage) advertisementsPane.getScene().getWindow();
            SceneManager.switchScene(stage, "my-advertisements.fxml");
        } catch (Exception e) {
            ShowAlert.showError("در باز نشان دادن آگهی های شما مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void openCreateAdvertisement() {

        try {
            Stage stage = (Stage) usernameLabel.getScene().getWindow();

            AdvertisementFormSession.openCreate();
            SceneManager.switchScene(stage,"advertisement-form.fxml");

        } catch (Exception e) {
            ShowAlert.showError("خطایی در ورود به صفحه درست کردن آگهی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void goToMessages() {

        try {
            Stage stage = (Stage) usernameLabel.getScene().getWindow();

            SceneManager.switchScene(stage, "messages.fxml");

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
        }
    }

    private void hideMessage() {
        messageLabel.setManaged(false);
        messageLabel.setVisible(false);
    }

    private void showMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setManaged(true);
        messageLabel.setVisible(true);
    }
}
