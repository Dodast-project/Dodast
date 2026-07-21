package com.example.dodast.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public final class SceneManager {

    private static final String VIEW_PATH = "/com/example/dodast/view/";

    private SceneManager() {
    }

    public static void switchScene(Stage stage, String fxmlFile) throws IOException {

        URL resource = SceneManager.class.getResource(VIEW_PATH + fxmlFile);

        if (resource == null) {
            throw new IOException("FXML file not found: " + fxmlFile);
        }

        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 600);

        URL css = SceneManager.class.getResource(VIEW_PATH + "app.css");
 
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}