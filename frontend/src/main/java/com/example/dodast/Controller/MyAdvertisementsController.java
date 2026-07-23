package com.example.dodast.Controller;

import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Util.AdvertisementCard;
import com.example.dodast.Util.AdvertisementFormSession;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.NavigationSession;
import com.example.dodast.Util.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.List;

public class MyAdvertisementsController {

    @FXML
    private FlowPane advertisementsPane;

    @FXML
    private Label messageLabel;

    private final AdvertisementService advertisementService = new AdvertisementService();

    @FXML
    public void initialize() {
        loadMyAdvertisements();
    }

    private void loadMyAdvertisements() {
        advertisementsPane.getChildren().clear();
        hideMessage();

        try {
            List<AdvertisementResponse> advertisements =advertisementService.getMyAdvertisements();

            if (advertisements == null || advertisements.isEmpty()) {
                showMessage("هنوز آگهی‌ای ثبت نکردید");
                return;
            }

            for (AdvertisementResponse advertisement : advertisements) {

                AdvertisementCard advertisementCard = new AdvertisementCard(advertisement, () -> showAdvertisementDetail(advertisement.getId()));

                advertisementCard.setShowStatus(true);
                advertisementCard.setShowManagementButtons(true);
                advertisementCard.setOnEdit(() -> openEditAdvertisement(advertisement.getId()));
                advertisementCard.setOnDelete(() -> deleteAdvertisement(advertisement.getId()));
                advertisementCard.setOnMarkAsSold(() -> markAdvertisementAsSold(advertisement.getId()));

                advertisementsPane.getChildren().add(advertisementCard.getView());
            }

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAdvertisementDetail(Long advertisementId) {

        try {
            AdvertisementSession.setSelectedAdvertisementId(advertisementId);
            NavigationSession.setPreviousPage("my-advertisements.fxml");

            Stage stage = getStage();
            SceneManager.switchScene(stage, "advertisement-detail.fxml");

        } catch (Exception e) {
            ShowAlert.showError("خطایی در نمایش آگهی پیش آمد");
            e.printStackTrace();
        }
    }

    private void openEditAdvertisement(Long advertisementId) {

        try {
            AdvertisementFormSession.openEdit(advertisementId);

            Stage stage = getStage();
            SceneManager.switchScene(stage, "advertisement-form.fxml");

        } catch (Exception e) {
            ShowAlert.showError("صفحه ویرایش آگهی باز نشد");
            e.printStackTrace();
        }
    }

    private void deleteAdvertisement(Long advertisementId) {
        try {
            advertisementService.deleteAdvertisement(advertisementId);
            loadMyAdvertisements();
            showMessage("آگهی با موفقیت حذف شد");
        } catch (Exception e) {
            ShowAlert.showError("در حذف آگهی مشکلی پیش آمد");
            e.printStackTrace();
        }
       
    }

    private void markAdvertisementAsSold(Long advertisementId) {

        try {
            advertisementService.markAsSold(advertisementId);
            loadMyAdvertisements();
            showMessage("وضعیت آگهی به فروخته‌شده تغییر کرد");
        } catch (Exception e) {
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            SceneManager.switchScene(getStage(), "home.fxml");
        } catch (Exception e) {
            ShowAlert.showError("در بازگشت به صفحه اصلی مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    private Stage getStage() {
        return (Stage) advertisementsPane.getScene().getWindow();
    }

    private void showMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setManaged(true);
        messageLabel.setVisible(true);
    }

    private void hideMessage() {
        messageLabel.setText("");
        messageLabel.setManaged(false);
        messageLabel.setVisible(false);
    }
}