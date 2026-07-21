package com.example.dodast.DTO.Rating;

public class SellerRatingSummaryResponse {

    private double averageScore;
    private long totalRatings;

    public SellerRatingSummaryResponse(double averageScore, long totalRatings) {
        this.averageScore = averageScore;
        this.totalRatings = totalRatings;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public long getTotalRatings() {
        return totalRatings;
    }
}