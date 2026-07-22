package com.example.dodast.Controller;

import com.example.dodast.DTO.Advertisement.AdvertisementDetailResponse;
import com.example.dodast.DTO.Advertisement.ImageResponse;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Service.FavoriteService;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.NavigationSession;
import com.example.dodast.Util.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Label messageLabel;

    @FXML
    private ImageView advertisementImage;

    private final AdvertisementService advertisementService = new AdvertisementService();
    private final FavoriteService favoriteService = new FavoriteService();

    @FXML
    private Button favoriteButton;

    private boolean favorite;

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
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAdvertisement(AdvertisementDetailResponse advertisement) {

        titleLabel.setText(safeText(advertisement.getTitle()));
        favorite = advertisement.isFavorite();
        updateFavoriteButton(favorite);
        descriptionLabel.setText(safeText(advertisement.getDescription()));

        if (advertisement.getPrice() != null) {
            priceLabel.setText(String.format("%,d تومان",advertisement.getPrice()));
        } else {
            priceLabel.setText("قیمت ثبت نشده");
        }

        locationLabel.setText(safeText(advertisement.getProvince()) + "، "+ safeText(advertisement.getCity()));
        categoryLabel.setText("دسته‌بندی: " + safeText(advertisement.getCategory()));
        loadFirstImage(advertisement);
    }

    private void loadFirstImage(AdvertisementDetailResponse advertisement) {
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
            ShowAlert.showError("خطایی در نمایش تصویر رخ داد");
            e.printStackTrace();
        }
    }

    @FXML
    private void backToHome() {
        try {
            AdvertisementSession.clear();

            Stage stage = (Stage) titleLabel.getScene().getWindow();

            SceneManager.switchScene(stage, NavigationSession.getPreviousPage());

        } catch (Exception e) {
            ShowAlert.showError("خطا در بازگشت به صفحه اصلی");
            e.printStackTrace();
        }
    }

    @FXML
    private void toggleFavorite() {

        Long advertisementId = AdvertisementSession.getSelectedAdvertisementId();

        if (advertisementId == null) {
            showMessage("در گرفتن شناسه آگهی مشکلی پیش آمد");
            return;
        }

        favoriteButton.setDisable(true);
        hideMessage();

        try {
            if (favorite) {
                favoriteService.removeFavorite(advertisementId);
                favorite = false;
            } else {
                favoriteService.addFavorite(advertisementId);
                favorite = true;
            }

            updateFavoriteButton(favorite);

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage() == null ? "خطا در تغییر وضعیت علاقه‌مندی" : e.getMessage());
            e.printStackTrace();
        } finally {
            favoriteButton.setDisable(false);
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

    private void hideMessage() {
        messageLabel.setText("");
        messageLabel.setVisible(false);
        messageLabel.setManaged(false);
    }

    private void updateFavoriteButton(boolean favorite) {

        if (favorite) {
            favoriteButton.setText("❤ حذف از علاقه‌مندی‌ها");
        } else {
            favoriteButton.setText("♡ افزودن به علاقه‌مندی‌ها");
        }
    }
}