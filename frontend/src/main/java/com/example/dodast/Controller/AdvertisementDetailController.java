package com.example.dodast.Controller;

import com.example.dodast.DTO.Advertisement.AdvertisementDetailResponse;
import com.example.dodast.DTO.Advertisement.ImageResponse;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Service.FavoriteService;
import com.example.dodast.Service.MessageApiService;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.NavigationSession;
import com.example.dodast.Util.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;

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
    private final MessageApiService messageApiService = new MessageApiService();

    @FXML
    private Button favoriteButton;

    private boolean favorite;

    @FXML
    private Button rateButton;

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
        try {
            locationLabel.setText(safeText(advertisementService.getProvinceById(advertisement.getProvinceId()).getName()) + "، "+ safeText(advertisementService.getCityById(advertisement.getCityId()).getName()));
            categoryLabel.setText("دسته‌بندی: " + safeText(advertisementService.getCategoryById(advertisement.getCategoryId()).getName()));
        } catch (Exception e) {
            locationLabel.setText("نامشخص");
            categoryLabel.setText("نامشخص");
            ShowAlert.showError("خطایی در بارگذاری دسته‌بندی یا شهر یا استان آگهی پیش آمد");
            e.printStackTrace();
        }

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
            ShowAlert.showError(e.getMessage());
            e.printStackTrace();
        } finally {
            favoriteButton.setDisable(false);
        }
    }

    @FXML
    private void openRatingDialog() {

        Long advertisementId = AdvertisementSession.getSelectedAdvertisementId();

        if (advertisementId == null) {
            ShowAlert.showError("شناسه آگهی مشخص نیست");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/dodast/view/rating-dialog.fxml"));
            Parent root = loader.load();

            RatingDialogController controller = loader.getController();
            controller.setAdvertisementId(advertisementId);

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("امتیاز به فروشنده");
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();

        } catch (Exception e) {
            ShowAlert.showError("در باز کردن پنجره امتیازدهی مشکلی پیش آمد");
            e.printStackTrace();
        }
    }

    @FXML
    private void openMessageSellerDialog() {

        Long advertisementId = AdvertisementSession.getSelectedAdvertisementId();

        if (advertisementId == null) {
            ShowAlert.showError("شناسه آگهی مشخص نیست");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("پیام به فروشنده");
        dialog.setHeaderText(null);
        dialog.setContentText("متن پیام:");

        Optional<String> result = dialog.showAndWait();

        if (result.isEmpty() || result.get().trim().isBlank()) {
            return;
        }

        try {
            messageApiService.sendMessage(advertisementId, result.get().trim());

            Stage stage = (Stage) titleLabel.getScene().getWindow();
            SceneManager.switchScene(stage, "messages.fxml");

        } catch (Exception e) {
            ShowAlert.showError(e.getMessage() != null ? e.getMessage() : "خطا در ارسال پیام به فروشنده");
            e.printStackTrace();
        }
    }

    private String safeText(String value) {
        if (value == null || value.isBlank()) return "نامشخص";
        return value;
    }

    private void showMessage(String message) {

        messageLabel.setText(message);
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