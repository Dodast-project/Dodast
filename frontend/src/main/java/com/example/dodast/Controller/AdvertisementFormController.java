package com.example.dodast.Controller;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.example.dodast.DTO.Advertisement.AdvertisementDetailResponse;
import com.example.dodast.DTO.Advertisement.UpdateAdvertisementRequest;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Model.AdvertisementFormMode;
import com.example.dodast.Service.AdvertisementService;
import com.example.dodast.Util.AdvertisementFormSession;
import com.example.dodast.Util.NavigationSession;
import com.example.dodast.Util.SceneManager;


public class AdvertisementFormController {

    @FXML
    private Label pageTitleLabel;

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
    private Label messageLabel;

    @FXML
    private VBox imageSection;

    @FXML
    private Button submitButton;

    @FXML
    private Label imageNameLabel;

    private File selectedImage;

    private final AdvertisementService advertisementService = new AdvertisementService();

    private Long advertisementId;

    @FXML
    private void initialize() {

        if (AdvertisementFormSession.getMode() == AdvertisementFormMode.CREATE) {
            imageSection.setManaged(true);
            imageSection.setVisible(true);
            initializeCreate();
        } else {
            imageSection.setManaged(false);
            imageSection.setVisible(false);
            initializeEdit();

            loadAdvertisement();
        }
    }

    private void initializeCreate() {

        pageTitleLabel.setText("ثبت آگهی جدید");
        submitButton.setText("ثبت آگهی");

        imageSection.setVisible(true);
        imageSection.setManaged(true);
    }

    private void initializeEdit() {

        pageTitleLabel.setText("ویرایش آگهی");
        submitButton.setText("ذخیره تغییرات");

        advertisementId = AdvertisementFormSession.getAdvertisementId();

        imageSection.setVisible(false);
        imageSection.setManaged(false);

        if (advertisementId == null) {
            showMessage("شناسه آگهی مشخص نیست");
            disableForm();
            return;
        }
        loadAdvertisement();
    }

    private void loadAdvertisement() {
        try {
            AdvertisementDetailResponse advertisement = advertisementService.getAdvertisementDetail(advertisementId);
            addFields(advertisement);

        } catch (RuntimeException e) {
            ShowAlert.showError(e.getMessage());
            disableForm();

        } catch (Exception e) {
            ShowAlert.showError("خطایی در بارگذاری آگهی پیش آمد");
            disableForm();
            e.printStackTrace();
        }
    }

    private void addFields(AdvertisementDetailResponse advertisement) {
        titleField.setText(advertisement.getTitle());
        descriptionField.setText(advertisement.getDescription());
        priceField.setText(String.valueOf(advertisement.getPrice()));
        categoryIdField.setText(advertisement.getCategory());
        provinceIdField.setText(advertisement.getProvince());
        cityIdField.setText(advertisement.getCity());
    }

    @FXML
    private void chooseImage() {
        if (AdvertisementFormSession.isEditMode()) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("انتخاب تصویر آگهی");
        File file = fileChooser.showOpenDialog(getStage());

        if (file != null) {
            selectedImage = file;
            imageNameLabel.setText(file.getName());
        }
    }

    @FXML
    private void handleSubmit() {

        hideMessage();

        if (AdvertisementFormSession.isEditMode()) {
            updateAdvertisement();
        } else {
            createAdvertisement();
        }
    }

    private void createAdvertisement() {

        submitButton.setDisable(true);

        try {
            advertisementService.createAdvertisement(titleField.getText().trim(),
                    descriptionField.getText().trim(),
                    Long.parseLong(priceField.getText().trim()),
                    Long.parseLong(categoryIdField.getText().trim()),
                    Long.parseLong(provinceIdField.getText().trim()),
                    Long.parseLong(cityIdField.getText().trim()), 
                    selectedImage
                );

            clearFields();

            showMessage("آگهی با موفقیت ثبت شد");

        } catch (RuntimeException e) {
            ShowAlert.showError(e.getMessage());

        } catch (Exception e) {
            ShowAlert.showError("خطایی در ارتباط با سرور پیش آمد");
            e.printStackTrace();

        } finally {
            submitButton.setDisable(false);
        }
    }

    private void updateAdvertisement() {

        submitButton.setDisable(true);

        try {
            UpdateAdvertisementRequest request = new UpdateAdvertisementRequest(titleField.getText().trim(),
                    descriptionField.getText().trim(),
                    Long.parseLong(priceField.getText().trim()),
                    Long.parseLong(categoryIdField.getText().trim()),
                    Long.parseLong(provinceIdField.getText().trim()),
                    Long.parseLong(cityIdField.getText().trim())
                );

            advertisementService.updateAdvertisement(advertisementId, request);

            AdvertisementFormSession.clear();

            SceneManager.switchScene(getStage(), "my-advertisements.fxml");

        } catch (RuntimeException e) {
            ShowAlert.showError(e.getMessage());

        } catch (Exception e) {
            ShowAlert.showError("تغییرات ذخیره نشد");
            e.printStackTrace();

        } finally {
            submitButton.setDisable(false);
        }
    }

    @FXML
    private void handleBack() {
        try {
            AdvertisementFormSession.clear();

            SceneManager.switchScene(getStage(), NavigationSession.getPreviousPage());

        } catch (Exception e) {
            ShowAlert.showError("در بازگشت به صفحه قبل مشکلی پیش آمد");
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

    private void disableForm() {

        titleField.setDisable(true);
        descriptionField.setDisable(true);
        priceField.setDisable(true);
        categoryIdField.setDisable(true);
        provinceIdField.setDisable(true);
        cityIdField.setDisable(true);
        submitButton.setDisable(true);
    }

    private Stage getStage() {
        return (Stage) titleField.getScene().getWindow();
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