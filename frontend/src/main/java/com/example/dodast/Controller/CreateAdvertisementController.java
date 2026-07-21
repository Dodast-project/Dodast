package com.example.dodast.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Util.SceneManager;

import java.io.File;
import java.net.http.HttpResponse;

public class CreateAdvertisementController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField categoryIdField;

    @FXML
    private TextField provinceIdField;

    @FXML
    private TextField cityIdField;

    @FXML
    private Label imageNameLabel;

    @FXML
    private Label messageLabel;

    private File selectedImage;

    private final AdvertisementService advertisementService = new AdvertisementService();

    @FXML
    private void chooseImage() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("انتخاب تصویر آگهی");

        selectedImage = fileChooser.showOpenDialog(titleField.getScene().getWindow());

        if (selectedImage != null) {
            imageNameLabel.setText(selectedImage.getName());
        }
    }


    @FXML
    private void createAdvertisement() {

        String title = titleField.getText().trim();
        String description = descriptionField.getText().trim();
        String price = priceField.getText().trim();
        String categoryId = categoryIdField.getText().trim();
        String provinceId = provinceIdField.getText().trim();
        String cityId = cityIdField.getText().trim();

        if (title.isBlank()
                || description.isBlank()
                || price.isBlank()
                || categoryId.isBlank()
                || provinceId.isBlank()
                || cityId.isBlank()) {

            showMessage("همه فیلدها را کامل کنید");
            return;
        }

        try {
            HttpResponse<String> response = advertisementService.createAdvertisement(
                            title,
                            description,
                            price,
                            categoryId,
                            provinceId,
                            cityId,
                            selectedImage
                        );
            
            if (response == null) {
                showMessage("آگهی با موفقیت ثبت شد");
                clearFields();
            } else {
                showMessage(response.body() + response.statusCode());
            }

        } catch (Exception e) {
            showMessage("خطا در ارتباط با سرور");
            e.printStackTrace();
        }
    }

    private void clearFields() {

        titleField.clear();
        descriptionField.clear();
        priceField.clear();
        categoryIdField.clear();
        provinceIdField.clear();
        cityIdField.clear();

        selectedImage = null;

        imageNameLabel.setText("تصویری انتخاب نشده");
    }

    @FXML
    private void backToHome() {
        try {
            Stage stage = (Stage) titleField.getScene().getWindow();

            SceneManager.switchScene(stage, "home.fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {
        messageLabel.setText(message);
    }
}