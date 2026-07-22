package com.example.dodast.Service;

import com.example.dodast.DTO.Advertisement.AdvertisementDetailResponse;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.DTO.Advertisement.UpdateAdvertisementRequest;
import com.example.dodast.Exception.GetAdvertisementError;
import com.example.dodast.Exception.UIException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.http.HttpResponse;
import java.util.List;

public class AdvertisementService {

    private final ApiClient apiClient;
    private final ObjectMapper mapper;

    public AdvertisementService() {
        apiClient = new ApiClient();
        mapper = new ObjectMapper();
    }

    public List<AdvertisementResponse> getActiveAdvertisements()throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements");

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), new TypeReference<List<AdvertisementResponse>>() {});
        }

        throw new GetAdvertisementError(response.statusCode());
    }

    public List<AdvertisementResponse> getMyAdvertisements() throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/me");

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), new TypeReference<List<AdvertisementResponse>>() {});
        }

        throw new UIException(response.body(), response.statusCode());
    }

    public AdvertisementDetailResponse getAdvertisementDetail(Long advertisementId) throws Exception {


        HttpResponse<String> response = apiClient.get("/advertisements/" + advertisementId);

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), AdvertisementDetailResponse.class);
        }

        throw new UIException(response.body(), response.statusCode());
    }

    public void createAdvertisement(String title,
            String description,
            Long price,
            Long categoryId,
            Long provinceId,
            Long cityId,
            File image) throws Exception {

        HttpResponse<String> response = apiClient.postAdvertisement(title,
                                                        description,
                                                        price,
                                                        categoryId,
                                                        provinceId,
                                                        cityId,
                                                        image);

        if (!isSuccessful(response)) {
            throw new UIException(response.body(), response.statusCode());
        }
    }

    public AdvertisementResponse updateAdvertisement(Long advertisementId, UpdateAdvertisementRequest request) throws Exception {

        String requestBody = mapper.writeValueAsString(request);

        HttpResponse<String> response = apiClient.put("/advertisements/" + advertisementId, requestBody);

        if (!isSuccessful(response)) throw new UIException(response.body(), response.statusCode());

        if (response.body() == null || response.body().isBlank()) return null;

        return mapper.readValue(response.body(), AdvertisementResponse.class);
    }

    public void deleteAdvertisement(Long advertisementId) throws Exception {

        HttpResponse<String> response = apiClient.delete("/advertisements/" + advertisementId);

        if (!isSuccessful(response)) throw new UIException(response.body(), response.statusCode());
    }

    public void markAsSold(Long advertisementId) throws Exception {

        HttpResponse<String> response = apiClient.patch("/advertisements/" + advertisementId + "/sold");

        if (!isSuccessful(response)) throw new UIException(response.body(), response.statusCode());
    }

    private boolean isSuccessful(HttpResponse<String> response) {
        return response.statusCode() >= 200 && response.statusCode() < 300;
    }

}