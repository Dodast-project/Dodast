package com.example.dodast.Exception;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;

public class ShowAlert {
    public static void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        alert.getDialogPane().setStyle("-fx-font-family: 'Arial';");
        alert.setTitle("خطا");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
