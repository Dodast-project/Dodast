package com.example.dodast.Controller;

import com.example.dodast.DTO.Message.MessageResponse;
import com.example.dodast.Service.MessageApiService;
import com.example.dodast.Util.SceneManager;
import com.example.dodast.Util.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class ChatController {

    @FXML
    private Label titleLabel;

    @FXML
    private ListView<MessageResponse> messagesListView;

    @FXML
    private TextField messageField;

    @FXML
    private Label errorLabel;

    private final MessageApiService messageApiService = new MessageApiService();

    private Long conversationId;
    private Long advertisementId;

    public void init(Long conversationId, Long advertisementId, String advertisementTitle, String otherUserFullName) {
        this.conversationId = conversationId;
        this.advertisementId = advertisementId;

        titleLabel.setText(advertisementTitle + "  |  " + otherUserFullName);

        messagesListView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(MessageResponse message, boolean empty) {
                super.updateItem(message, empty);

                if (empty || message == null) {
                    setText(null);
                } else {
                    boolean isMine = message.getSenderId().equals(SessionManager.getUserId());
                    String prefix = isMine ? "من: " : message.getSenderName() + ": ";
                    setText(prefix + message.getText());
                }
            }
        });

        loadMessages();
    }

    private void loadMessages() {
        try {
            List<MessageResponse> messages = messageApiService.getMessages(conversationId);
            messagesListView.getItems().setAll(messages);
            if (!messages.isEmpty()) {
                messagesListView.scrollTo(messages.size() - 1);
            }
        } catch (Exception e) {
            showError("خطا در دریافت پیام‌ها");
            e.printStackTrace();
        }
    }

    @FXML
    private void sendMessage() {
        String text = messageField.getText().trim();

        if (text.isBlank()) {
            showError("متن پیام نمی‌تواند خالی باشد");
            return;
        }

        try {
            messageApiService.sendMessage(advertisementId, text);
            messageField.clear();
            loadMessages();
        } catch (Exception e) {
            showError("خطا در ارسال پیام");
            e.printStackTrace();
        }
    }

    @FXML
    private void backToMessages() {
        try {
            Stage stage = (Stage) messagesListView.getScene().getWindow();
            SceneManager.switchScene(stage, "messages.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
        }
    }
}
