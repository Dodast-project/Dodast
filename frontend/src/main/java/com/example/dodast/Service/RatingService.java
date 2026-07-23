package com.example.dodast.Service;

import com.example.dodast.DTO.Rating.CreateRatingRequest;
import com.example.dodast.DTO.Rating.RatingResponse;
import com.example.dodast.DTO.Rating.SellerRatingSummaryResponse;
import com.example.dodast.Exception.ExceptionCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;
import java.util.List;

public class RatingService {

    private final ApiClient apiClient = new ApiClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public RatingResponse createRating(CreateRatingRequest request) throws Exception {

        String json = mapper.writeValueAsString(request);
        HttpResponse<String> response = apiClient.post("/ratings", json);

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return mapper.readValue(response.body(), RatingResponse.class);
        }

        throw ExceptionCreator.createException(response);
    }

    public List<RatingResponse> getRatingsForSeller(Long sellerId) throws Exception {

        HttpResponse<String> response = apiClient.get("/ratings/seller/" + sellerId);

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return mapper.readValue(response.body(), new TypeReference<List<RatingResponse>>() {});
        }

        throw ExceptionCreator.createException(response);
    }

    public SellerRatingSummaryResponse getSellerSummary(Long sellerId) throws Exception {

        HttpResponse<String> response = apiClient.get("/ratings/seller/" + sellerId + "/summary");

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return mapper.readValue(response.body(), SellerRatingSummaryResponse.class);
        }

        throw ExceptionCreator.createException(response);
    }
}
