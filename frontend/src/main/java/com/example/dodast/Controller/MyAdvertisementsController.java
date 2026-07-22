package com.example.dodast.Controller;

import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Service.MyAdvertisementsService;
import com.example.dodast.Util.AdvertisementCard;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.NavigationSession;
import com.example.dodast.Util.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.List;

public class MyAdvertisementsController {

    @FXML
    private FlowPane advertisementsPane;

    private final MyAdvertisementsService myAdvertisementsService = new MyAdvertisementsService();

    @FXML
    public void initialize() {
        loadMyAdvertisements();
    }

    private void loadMyAdvertisements() {

        try {
            List<AdvertisementResponse> advertisements = myAdvertisementsService.getMyAdvertisements();

            advertisementsPane.getChildren().clear();

            for (AdvertisementResponse advertisement : advertisements) {
                AdvertisementCard advertisementCard = new AdvertisementCard(advertisement, () -> showAdvertisementDetail(advertisement.getId()));
                advertisementsPane.getChildren().add(advertisementCard.getView());
                advertisementCard.setShowStatus(true);
                advertisementCard.setShowManagementButtons(true);
            }

        } catch (Exception e) {
            showError("در دریافت آگهی شما مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    private void showAdvertisementDetail(Long advertisementId) {

        try {
            AdvertisementSession.setSelectedAdvertisementId(advertisementId);
            NavigationSession.setPreviousPage("my-advertisements.fxml");
            Stage stage = (Stage) advertisementsPane.getScene().getWindow();
            SceneManager.switchScene(stage, "advertisement-detail.fxml");
        } catch (Exception e) {
            
        }
        
    }

    @FXML
    private void handleBack() {
        try {
            Stage stage = (Stage) advertisementsPane.getScene().getWindow();
            SceneManager.switchScene(stage, "home.fxml");
        } catch (Exception e) {
            showError("در بازگشت به صفحه قبل مشکلی پیش آمد");
            e.printStackTrace();
        }
        
    }

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("خطا");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}