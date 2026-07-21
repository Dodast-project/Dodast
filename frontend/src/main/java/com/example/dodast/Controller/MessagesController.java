package com.example.dodast.Controller;

import com.example.dodast.DTO.Message.ConversationResponse;
import com.example.dodast.Service.MessageApiService;
import com.example.dodast.Util.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class MessagesController {

    @FXML
    private ListView<ConversationResponse> conversationsListView;

    @FXML
    private Label messageLabel;

    private final MessageApiService messageApiService = new MessageApiService();

    @FXML
    private void initialize() {

        conversationsListView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(ConversationResponse conversation, boolean empty) {
                super.updateItem(conversation, empty);

                if (empty || conversation == null) {
                    setText(null);
                } else {
                    String preview = conversation.getLastMessage() == null ? "" : conversation.getLastMessage();
                    setText(conversation.getAdvertisementTitle()
                            + "  |  " + conversation.getOtherUserFullName()
                            + "\n" + preview);
                }
            }
        });

        conversationsListView.setOnMouseClicked(event -> {
            ConversationResponse selected = conversationsListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                openChat(selected);
            }
        });

        loadConversations();
    }

    private void loadConversations() {
        try {
            List<ConversationResponse> conversations = messageApiService.getConversations();
            conversationsListView.setItems(FXCollections.observableArrayList(conversations));
        } catch (Exception e) {
            showMessage("خطا در دریافت گفت‌وگوها");
            e.printStackTrace();
        }
    }

    private void openChat(ConversationResponse conversation) {
        try {
            Stage stage = (Stage) conversationsListView.getScene().getWindow();
            Object controller = SceneManager.switchSceneReturningController(stage, "chat.fxml");

            if (controller instanceof ChatController chatController) {
                chatController.init(
                        conversation.getId(),
                        conversation.getAdvertisementId(),
                        conversation.getAdvertisementTitle(),
                        conversation.getOtherUserFullName()
                );
            }
        } catch (Exception e) {
            showMessage("خطا در بازکردن گفت‌وگو");
            e.printStackTrace();
        }
    }

    @FXML
    private void backToHome() {
        try {
            Stage stage = (Stage) conversationsListView.getScene().getWindow();
            SceneManager.switchScene(stage, "home.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {
        if (messageLabel != null) {
            messageLabel.setText(message);
        }
    }
}
