package com.example.dodast.Controller;

import com.example.dodast.Util.AdvertisementCard;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Service.FavoriteService;
import com.example.dodast.Util.AdvertisementSession;
import com.example.dodast.Util.NavigationSession;
import com.example.dodast.Util.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.List;

public class FavoritesController {

    @FXML
    private FlowPane favoritesPane;

    @FXML
    private Label messageLabel;

    private final FavoriteService favoriteService = new FavoriteService();

    @FXML
    private void initialize() {
        loadFavorites();
    }

    private void loadFavorites() {

        hideMessage();

        try {
            List<AdvertisementResponse> favorites = favoriteService.getFavorites();

            favoritesPane.getChildren().clear();

            if (favorites == null || favorites.isEmpty()) {
                showMessage("هنوز آگهی‌ای به علاقه‌مندی‌ها اضافه نکردید");
                return;
            }

            for (AdvertisementResponse advertisement : favorites) {
                favoritesPane.getChildren().add(AdvertisementCard.createAdvertisementCard(advertisement, () -> showAdvertisementDetail(advertisement.getId())));
            }

        } catch (Exception e) {
            showMessage(e.getMessage() == null ? "خطا در دریافت علاقه‌مندی‌ها" : e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAdvertisementDetail(Long advertisementId){

        try{
            AdvertisementSession.setSelectedAdvertisementId(advertisementId);
            NavigationSession.setPreviousPage("favorites.fxml");
            Stage stage = (Stage) favoritesPane.getScene().getWindow();
            SceneManager.switchScene(stage, "advertisement-detail.fxml");
        }
        catch(Exception e){
            showMessage("خطایی در نشان دادن آگهی پیش آمد");
            e.printStackTrace();
        }
        
    }

    @FXML
    private void backToHome(){
        try {
            Stage stage = (Stage) favoritesPane.getScene().getWindow();
            SceneManager.switchScene(stage, "home.fxml");
        } catch (Exception e) {
            showMessage("خطایی در برگشت به خانه پیش آمد");
            e.printStackTrace();
        }
        
    }

    @FXML
    private void refreshFavorites() {
        loadFavorites();
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
}