package com.example.dodast.DTO.Message;

public class SendMessageRequest {

    private Long advertisementId;

    private String text;

    public SendMessageRequest() {
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
