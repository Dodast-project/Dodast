package com.example.dodast;

import com.example.dodast.Util.OnlineFontLoader;
import com.example.dodast.Util.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        OnlineFontLoader.load();
        
        stage.setTitle("Dodast");
        stage.setMinWidth(1400);
        stage.setMinHeight(850);

        SceneManager.switchScene(stage, "login.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}