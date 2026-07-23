package com.example.dodast.Controller;

import com.example.dodast.DTO.Rating.CreateRatingRequest;
import com.example.dodast.Exception.ShowAlert;
import com.example.dodast.Service.RatingService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class RatingDialogController {

    @FXML
    private ComboBox<Integer> scoreComboBox;

    @FXML
    private TextArea commentField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button submitButton;

    private final RatingService ratingService = new RatingService();

    private Long advertisementId;

    @FXML
    private void initialize() {
        scoreComboBox.getItems().addAll(1, 2, 3, 4, 5);
        scoreComboBox.setValue(5);
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

    @FXML
    private void submitRating() {

        hideMessage();

        Integer score = scoreComboBox.getValue();

        if (score == null) {
            showMessage("انتخاب امتیاز الزامی است");
            return;
        }

        submitButton.setDisable(true);

        try {
            CreateRatingRequest request = new CreateRatingRequest(
                    advertisementId,
                    score,
                    commentField.getText().trim()
            );

            ratingService.createRating(request);

            closeDialog();

        } catch (RuntimeException e) {
            ShowAlert.showError(e.getMessage());

        } catch (Exception e) {
            ShowAlert.showError("خطایی در ثبت امتیاز پیش آمد");
            e.printStackTrace();

        } finally {
            submitButton.setDisable(false);
        }
    }

    @FXML
    private void cancel() {
        closeDialog();
    }

    private void closeDialog() {
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
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
