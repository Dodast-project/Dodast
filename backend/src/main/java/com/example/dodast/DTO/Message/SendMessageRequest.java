package com.example.dodast.DTO.Message;

import jakarta.validation.constraints.NotBlank;

public class SendMessageRequest {

    private Long advertisementId;

    private Long conversationId;

    @NotBlank
    private String text;

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
