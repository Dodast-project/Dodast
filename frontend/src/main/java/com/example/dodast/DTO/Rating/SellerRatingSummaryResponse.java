package com.example.dodast.DTO.Rating;

public class SellerRatingSummaryResponse {

    private double averageScore;
    private long totalRatings;

    public SellerRatingSummaryResponse() {
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public long getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(long totalRatings) {
        this.totalRatings = totalRatings;
    }
}
