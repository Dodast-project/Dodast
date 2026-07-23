package com.example.dodast.Controller;

import java.util.List;

import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Util.AdvertisementCard;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.NavigationSession;
import com.example.dodast.Util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class AdminPendingAdvertisementsController {

    @FXML
    private FlowPane advertisementsPane;

    @FXML
    private Label messageLabel;

    private final AdvertisementService advertisementService = new AdvertisementService();

    @FXML
    public void initialize() {

        loadAdvertisements();
    }

    private void loadAdvertisements() {
        advertisementsPane.getChildren().clear();

        hideMessage();

        try {
            List<AdvertisementResponse> advertisements = advertisementService.getPendingAdvertisements();

            if (advertisements == null || advertisements.isEmpty()) {
                showMessage("آگهی در انتظار تأییدی وجود ندارد");
                return;
            }

            for (AdvertisementResponse advertisement : advertisements) {
                AdvertisementCard card = new AdvertisementCard(advertisement, () -> openAdvertisementDetail(advertisement.getId()));
                card.setShowAdminButtons(true);
                card.setOnApprove(() -> approveAdvertisement(advertisement.getId()));
                card.setOnReject(() -> rejectAdvertisement(advertisement.getId()));
                advertisementsPane.getChildren().add(card.getView());
            }

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();

        }
    }

    @FXML
    private void goBack() {
        try {
            Stage stage = (Stage) messageLabel.getScene().getWindow();
            SceneManager.switchScene(stage,"admin-dashboard.fxml");

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();

        }

    }

    private void approveAdvertisement(Long advertisementId) {
        try {
            advertisementService.approveAdvertisement(advertisementId);
            loadAdvertisements();

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        }
    }

    private void rejectAdvertisement(Long advertisementId) {
        try {
            advertisementService.rejectAdvertisement(advertisementId);
            loadAdvertisements();

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        }
    }

    private void openAdvertisementDetail(Long advertisementId) {
        try {
            AdvertisementSession.setSelectedAdvertisementId(advertisementId);

            Stage stage = (Stage) advertisementsPane.getScene().getWindow();
            NavigationSession.setPreviousPage("admin-pending-advertisement.fxml");
            SceneManager.switchScene(stage, "advertisement-detail.fxml");

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        }
    }


    private void showMessage(String message) {
        messageLabel.setManaged(true);
        messageLabel.setVisible(true);
        messageLabel.setText(message);
    }

    private void hideMessage() {
        messageLabel.setManaged(false);
        messageLabel.setVisible(false);
    }
}

