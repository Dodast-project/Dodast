package com.example.dodast.DTO.Message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SendMessageRequest {

    @NotNull
    private Long advertisementId;

    @NotBlank
    private String text;

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public String getText() {
        return text;
    }
}
