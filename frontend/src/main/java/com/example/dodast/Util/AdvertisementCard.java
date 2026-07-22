package com.example.dodast.Util;

import com.example.dodast.DTO.Advertisement.AdvertisementResponse;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdvertisementCard {

    private static final String BASE_URL = "http://localhost:8080";

    private final AdvertisementResponse advertisement;

    private Runnable onEdit;
    private Runnable onDelete;
    private Runnable onMarkAsSold;

    private final VBox card;
    private final Label statusLabel;
    private final Button editButton;
    private final Button deleteButton;
    private final Button soldButton;

    public AdvertisementCard(AdvertisementResponse advertisement,Runnable onClick) {
        this.advertisement = advertisement;

        ImageView imageView = createImageView();

        Label titleLabel = new Label(advertisement.getTitle());
        Label cityLabel = new Label("شهر: " + advertisement.getCity());

        Label favoriteLabel = new Label(advertisement.isFavorite() ? "❤" : "♡");

        statusLabel = new Label("وضعیت: " + getStatusText(advertisement.getStatus()));

        editButton = new Button("ویرایش");
        deleteButton = new Button("حذف");
        soldButton = new Button("فروخته شد");

        HBox managementButtons = new HBox(8, editButton, deleteButton, soldButton);

        card = new VBox(10, imageView, titleLabel, cityLabel, favoriteLabel, statusLabel, managementButtons);

        card.setPadding(new Insets(10));
        card.setPrefWidth(240);
        card.getStyleClass().add("advertisement-card");
        card.setStyle("-fx-cursor: hand;");

        card.setOnMouseClicked(event -> onClick.run());

        editButton.setOnAction(event -> {
            event.consume();
            onEdit.run();
        });

        deleteButton.setOnAction(event -> {
            event.consume();
            onDelete.run();
        });

        soldButton.setOnAction(event -> {
            event.consume();
            onMarkAsSold.run();
        });

        setShowStatus(false);
        setShowManagementButtons(false);
    }

    private ImageView createImageView() {

        ImageView imageView = new ImageView();

        String imagePath = advertisement.getImageUrl();

        if (imagePath != null && !imagePath.isBlank()) {
            String fullImageUrl = BASE_URL + imagePath;
            Image image = new Image(fullImageUrl, true);
            imageView.setImage(image);
        }

        imageView.setFitWidth(220);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(false);

        return imageView;
    }

    public VBox getView() {
        return card;
    }

    public void setShowStatus(boolean showStatus) {
        statusLabel.setVisible(showStatus);
        statusLabel.setManaged(showStatus);
    }

    public void setShowManagementButtons(boolean showManagementButtons) {
        editButton.setVisible(showManagementButtons);
        editButton.setManaged(showManagementButtons);

        deleteButton.setVisible(showManagementButtons);
        deleteButton.setManaged(showManagementButtons);

        boolean canMarkAsSold = showManagementButtons && "ACTIVE".equals(advertisement.getStatus());

        soldButton.setVisible(canMarkAsSold);
        soldButton.setManaged(canMarkAsSold);
    }

    public void setOnEdit(Runnable onEdit) {
        this.onEdit = onEdit;
    }

    public void setOnDelete(Runnable onDelete) {
        this.onDelete = onDelete;
    }

    public void setOnMarkAsSold(Runnable onMarkAsSold) {
        this.onMarkAsSold = onMarkAsSold;
    }

    private String getStatusText(String status) {

        if (status == null) {
            return "نامشخص";
        }

        return switch (status) {
            case "PENDING" -> "در انتظار تأیید";
            case "ACTIVE" -> "فعال";
            case "SOLD" -> "فروخته‌شده";
            case "REJECTED" -> "ردشده";
            case "DELETED" -> "حذف‌شده";
            default -> status;
        };
    }
}