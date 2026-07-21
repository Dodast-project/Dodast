package com.example.dodast.DTO.Message;

import java.time.LocalDateTime;

public class ConversationResponse {

    private Long id;
    private Long advertisementId;
    private String advertisementTitle;
    private String otherUserFullName;
    private String lastMessage;
    private LocalDateTime lastMessageAt;

    public ConversationResponse() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAdvertisementId() { return advertisementId; }
    public void setAdvertisementId(Long advertisementId) { this.advertisementId = advertisementId; }

    public String getAdvertisementTitle() { return advertisementTitle; }
    public void setAdvertisementTitle(String advertisementTitle) { this.advertisementTitle = advertisementTitle; }

    public String getOtherUserFullName() { return otherUserFullName; }
    public void setOtherUserFullName(String otherUserFullName) { this.otherUserFullName = otherUserFullName; }

    public String getLastMessage() { return lastMessage; }
    public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }

    public LocalDateTime getLastMessageAt() { return lastMessageAt; }
    public void setLastMessageAt(LocalDateTime lastMessageAt) { this.lastMessageAt = lastMessageAt; }
}
