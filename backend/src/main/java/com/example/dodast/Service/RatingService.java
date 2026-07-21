package com.example.dodast.Service;

import com.example.dodast.DTO.Rating.CreateRatingRequest;
import com.example.dodast.DTO.Rating.RatingResponse;
import com.example.dodast.DTO.Rating.SellerRatingSummaryResponse;
import com.example.dodast.Exception.AdvertisementNotFoundException;
import com.example.dodast.Exception.DuplicateRatingException;
import com.example.dodast.Exception.InvalidRatingValueException;
import com.example.dodast.Exception.SelfRatingException;
import com.example.dodast.Model.Advertisement;
import com.example.dodast.Model.Rating;
import com.example.dodast.Model.User;
import com.example.dodast.Repository.AdvertisementRepository;
import com.example.dodast.Repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final AdvertisementRepository advertisementRepository;

    public RatingResponse createRating(CreateRatingRequest request) {

        Advertisement advertisement = advertisementRepository.findById(request.getAdvertisementId())
                .orElseThrow(AdvertisementNotFoundException::new);

        User currentUser = AdAuthenticator.getCurrentUser();
        User seller = advertisement.getOwner();

        if (currentUser.getId().equals(seller.getId())) {
            throw new SelfRatingException();
        }

        if (request.getScore() == null || request.getScore() < 1 || request.getScore() > 5) {
            throw new InvalidRatingValueException();
        }

        if (ratingRepository.existsByBuyerIdAndAdvertisementId(currentUser.getId(), advertisement.getId())) {
            throw new DuplicateRatingException();
        }

        Rating rating = Rating.builder()
                .buyer(currentUser)
                .seller(seller)
                .advertisement(advertisement)
                .score(request.getScore())
                .comment(request.getComment())
                .build();

        ratingRepository.save(rating);

        return new RatingResponse(
                rating.getId(),
                currentUser.getFullName(),
                rating.getScore(),
                rating.getComment(),
                rating.getCreatedAt()
        );
    }

    public List<RatingResponse> getRatingsForSeller(Long sellerId) {

        List<Rating> ratings = ratingRepository.findBySellerIdOrderByCreatedAtDesc(sellerId);

        List<RatingResponse> result = new ArrayList<>();

        for (Rating rating : ratings) {
            result.add(new RatingResponse(
                    rating.getId(),
                    rating.getBuyer().getFullName(),
                    rating.getScore(),
                    rating.getComment(),
                    rating.getCreatedAt()
            ));
        }

        return result;
    }

    public SellerRatingSummaryResponse getSellerSummary(Long sellerId) {

        Double average = ratingRepository.findAverageScoreBySellerId(sellerId);
        long total = ratingRepository.countBySellerId(sellerId);

        return new SellerRatingSummaryResponse(
                average != null ? average : 0.0,
                total
        );
    }
}
