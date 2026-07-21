package com.example.dodast.DTO.Rating;

import java.time.LocalDateTime;

public class RatingResponse {

    private Long id;
    private String buyerName;
    private int score;
    private String comment;
    private LocalDateTime createdAt;

    public RatingResponse(
            Long id,
            String buyerName,
            int score,
            String comment,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.buyerName = buyerName;
        this.score = score;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
