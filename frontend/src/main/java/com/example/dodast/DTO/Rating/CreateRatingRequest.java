package com.example.dodast.DTO.Rating;

public class CreateRatingRequest {

    private Long advertisementId;
    private Integer score;
    private String comment;

    public CreateRatingRequest(Long advertisementId, Integer score, String comment) {
        this.advertisementId = advertisementId;
        this.score = score;
        this.comment = comment;
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public Integer getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }
}
