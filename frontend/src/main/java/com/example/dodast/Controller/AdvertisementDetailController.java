package com.example.dodast.Controller;

import com.example.dodast.DTO.Advertisement.AdvertisementDetailResponse;
import com.example.dodast.DTO.Advertisement.ImageResponse;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AdvertisementDetailController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label favoriteLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private ImageView advertisementImage;

    private final AdvertisementService advertisementService =
            new AdvertisementService();

    @FXML
    private void initialize() {

        Long advertisementId = AdvertisementSession.getSelectedAdvertisementId();

        if (advertisementId == null) {
            showMessage("هیچ آگهی‌ای انتخاب نشده است");
            return;
        }

        loadAdvertisement(advertisementId);
    }

    private void loadAdvertisement(Long advertisementId) {
        try {

            AdvertisementDetailResponse advertisement = advertisementService.getAdvertisementDetail(advertisementId);

            showAdvertisement(advertisement);

        } catch (Exception e) {

            showMessage(e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAdvertisement(AdvertisementDetailResponse advertisement) {

        titleLabel.setText(safeText(advertisement.getTitle()));

        descriptionLabel.setText(safeText(advertisement.getDescription()));

        if (advertisement.getPrice() != null) {
            priceLabel.setText(String.format("%,d تومان",advertisement.getPrice()));
        } else {
            priceLabel.setText("قیمت ثبت نشده");
        }

        locationLabel.setText(safeText(advertisement.getProvince()) + "، "+ safeText(advertisement.getCity()));

        categoryLabel.setText("دسته‌بندی: " + safeText(advertisement.getCategory()));

        favoriteLabel.setText(advertisement.isFavorite() ? "در علاقه‌مندی‌ها" : "در علاقه‌مندی‌ها نیست");

        loadFirstImage(advertisement);
    }

    private void loadFirstImage(AdvertisementDetailResponse advertisement) {

        hideError();

        if (advertisement.getImages() == null || advertisement.getImages().isEmpty()) return;

        ImageResponse imageResponse = advertisement.getImages().get(0);

        if (imageResponse == null || imageResponse.getImageUrl() == null || imageResponse.getImageUrl().isBlank()){
            advertisementImage.setVisible(false);
            advertisementImage.setManaged(false);
            return;
        }

        String imageUrl = imageResponse.getImageUrl();

        imageUrl = "http://localhost:8080" + imageUrl;

        try {
            Image image = new Image(imageUrl, true);

            advertisementImage.setImage(image);

        } catch (Exception e) {
            showMessage("خطایی در نمایش تصویر رخ داد");
        }
    }

    @FXML
    private void backToHome() {
        try {
            AdvertisementSession.clear();

            Stage stage = (Stage) titleLabel.getScene().getWindow();

            SceneManager.switchScene(stage, "home.fxml");

        } catch (Exception e) {
            showMessage("خطا در بازگشت به صفحه اصلی");
            e.printStackTrace();
        }
    }

    private String safeText(String value) {

        if (value == null || value.isBlank()) return "نامشخص";

        return value;
    }

    private void showMessage(String message) {

        messageLabel.setText(message == null ? "خطا در دریافت اطلاعات آگهی" : message);

        messageLabel.setVisible(true);
        messageLabel.setManaged(true);
    }

    private void hideError() {
        messageLabel.setText("");
        messageLabel.setVisible(false);
        messageLabel.setManaged(false);
    }
}