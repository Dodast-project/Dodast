package com.example.dodast.DTO.Message;

import java.time.LocalDateTime;

public class ConversationResponse {

    private Long conversationId;

    private Long advertisementId;

    private String advertisementTitle;

    private String otherUserName;

    private String lastMessage;

    private LocalDateTime lastMessageAt;

    public ConversationResponse(
            Long conversationId,
            Long advertisementId,
            String advertisementTitle,
            String otherUserName,
            String lastMessage,
            LocalDateTime lastMessageAt
    ) {
        this.conversationId = conversationId;
        this.advertisementId = advertisementId;
        this.advertisementTitle = advertisementTitle;
        this.otherUserName = otherUserName;
        this.lastMessage = lastMessage;
        this.lastMessageAt = lastMessageAt;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public String getAdvertisementTitle() {
        return advertisementTitle;
    }

    public String getOtherUserName() {
        return otherUserName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public LocalDateTime getLastMessageAt() {
        return lastMessageAt;
    }
}
