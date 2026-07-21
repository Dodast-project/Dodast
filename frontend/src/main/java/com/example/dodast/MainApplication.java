package com.example.dodast;

import com.example.dodast.Util.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Dodast");
        stage.setMinWidth(800);
        stage.setMinHeight(550);

        SceneManager.switchScene(stage, "login.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}