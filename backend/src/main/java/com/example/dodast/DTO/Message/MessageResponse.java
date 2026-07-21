package com.example.dodast.DTO.Message;

import java.time.LocalDateTime;

public class MessageResponse {

    private Long id;

    private Long senderId;

    private String senderName;

    private String text;

    private LocalDateTime sentAt;

    public MessageResponse(
            Long id,
            Long senderId,
            String senderName,
            String text,
            LocalDateTime sentAt
    ) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.text = text;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}