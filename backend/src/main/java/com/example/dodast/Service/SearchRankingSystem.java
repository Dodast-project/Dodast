package com.example.dodast.Service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.Enums.SearchSortBy;
import com.example.dodast.Repository.RatingRepository;

@Component
public class SearchRankingSystem {

    private final RatingRepository ratingRepository;

    public SearchRankingSystem(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Sort createSort(SearchSortBy sortBy){
        
        switch (sortBy) {
            case LOWESTPRICE:
                return Sort.by(Sort.Direction.ASC, "price");

            case HIGHESTPRICE:
                return Sort.by(Sort.Direction.DESC, "price");

            case NEWEST:
                return Sort.by(Sort.Direction.DESC, "createdAt");
                
            default:
                return Sort.unsorted();
        }
    }

    public List<Advertisement> sortByBestMatch(List<Advertisement> ads, String keyword) {
        ads.sort((a, b) -> {
            int aScore = calculateScore(a, keyword);
            int bScore = calculateScore(b, keyword);

            if(a.getCreatedAt() != null && b.getCreatedAt() != null){
                if (a.getCreatedAt().isAfter(b.getCreatedAt())) {
                    aScore++;
                } else if (b.getCreatedAt().isAfter(a.getCreatedAt())) {
                    bScore++;
                }
            }
            return Integer.compare(bScore, aScore);
        });
        return ads;
    }

    public List<Advertisement> sortBySellerRating(List<Advertisement> ads) {
        ads.sort((a, b) -> {
                double aRating = 0;
                double bRating = 0;
                if (a.getOwner() != null && ratingRepository.findAverageScoreBySellerId(a.getOwner().getId()) != null) {
                    aRating = ratingRepository.findAverageScoreBySellerId(a.getOwner().getId());
                }
                if (b.getOwner() != null && ratingRepository.findAverageScoreBySellerId(b.getOwner().getId()) != null) {
                    bRating = ratingRepository.findAverageScoreBySellerId(b.getOwner().getId());
                }
                return Double.compare(bRating, aRating);
            });
        return ads;
    }

    private int calculateScore(Advertisement ad, String keyword) {
        int score = 0;

        String title = ad.getTitle().toLowerCase().trim();
        String description = ad.getDescription().toLowerCase().trim();
        keyword = keyword.toLowerCase().trim();

        if (title.equals(keyword)) {
            score += 100;
        }
        if (description.equals(keyword)) {
            score += 70;
        }
        if (title.contains(keyword)) {
            score += 50;
        }
        if (description.contains(keyword)) {
            score += 30;
        }

        int ratingScore = 0;

        if (ad.getOwner() != null) {
            Double averageRating = ratingRepository.findAverageScoreBySellerId(ad.getOwner().getId());
            if (averageRating != null) {
                ratingScore = (int) Math.round(averageRating * 10); // Scale the rating to a score out of 50 (5 stars * 10)
            }
        }

        score += ratingScore;

        return score;
    }
}