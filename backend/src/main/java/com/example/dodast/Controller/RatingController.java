package com.example.dodast.Controller;

import com.example.dodast.DTO.Rating.CreateRatingRequest;
import com.example.dodast.DTO.Rating.RatingResponse;
import com.example.dodast.DTO.Rating.SellerRatingSummaryResponse;
import com.example.dodast.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public RatingResponse createRating(@Valid @RequestBody CreateRatingRequest request) {
        return ratingService.createRating(request);
    }

    @GetMapping("/seller/{sellerId}")
    public List<RatingResponse> getRatingsForSeller(@PathVariable Long sellerId) {
        return ratingService.getRatingsForSeller(sellerId);
    }

    @GetMapping("/seller/{sellerId}/summary")
    public SellerRatingSummaryResponse getSellerSummary(@PathVariable Long sellerId) {
        return ratingService.getSellerSummary(sellerId);
    }
}