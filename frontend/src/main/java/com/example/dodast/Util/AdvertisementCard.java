package com.example.dodast.Util;

import com.example.dodast.DTO.Advertisement.AdvertisementResponse;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class AdvertisementCard {

    public static final String BASE_URL = "http://localhost:8080";

    public static VBox createAdvertisementCard(AdvertisementResponse advertisement) {

        ImageView imageView = new ImageView();

        String imagePath = advertisement.getImageUrl();

        if (imagePath != null && !imagePath.isBlank()) {

            String fullImageUrl = "http://localhost:8080" + imagePath;

            Image image = new Image(fullImageUrl, true);

            imageView.setImage(image);
        }

        imageView.setFitWidth(220);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(false);

        Label titleLabel = new Label(advertisement.getTitle());

        Label cityLabel = new Label("شهر " + advertisement.getCity());

        Label favoriteLabel;

        if (advertisement.isFavorite()) {
            favoriteLabel = new Label("❤");
        } else {
            favoriteLabel = new Label("♡");
        }

        VBox card = new VBox(
                10,
                imageView,
                titleLabel,
                cityLabel,
                favoriteLabel
        );

        card.setPadding(new Insets(10));
        card.setPrefWidth(220);

        card.getStyleClass().add("advertisement-card");

        return card;
    }
}
