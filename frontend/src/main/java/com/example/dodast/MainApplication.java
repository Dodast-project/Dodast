package com.example.dodast;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Dodast Frontend is running");

        Scene scene = new Scene(label, 600, 400);

        stage.setTitle("Dodast");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}