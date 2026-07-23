package com.example.dodast.DTO.Message;

import java.time.LocalDateTime;

public class ConversationResponse {

    private Long id;

    private Long advertisementId;

    private String advertisementTitle;

    private String otherUserFullName;

    private String lastMessage;

    private LocalDateTime lastMessageAt;

    public ConversationResponse(
            Long id,
            Long advertisementId,
            String advertisementTitle,
            String otherUserFullName,
            String lastMessage,
            LocalDateTime lastMessageAt
    ) {
        this.id = id;
        this.advertisementId = advertisementId;
        this.advertisementTitle = advertisementTitle;
        this.otherUserFullName = otherUserFullName;
        this.lastMessage = lastMessage;
        this.lastMessageAt = lastMessageAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public String getAdvertisementTitle() {
        return advertisementTitle;
    }

    public String getOtherUserFullName() {
        return otherUserFullName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public LocalDateTime getLastMessageAt() {
        return lastMessageAt;
    }
}
