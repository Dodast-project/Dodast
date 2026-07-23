package com.example.dodast.Service;

import com.example.dodast.DTO.Advertisement.AdvertisementDetailResponse;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.DTO.Advertisement.OptionResponse;
import com.example.dodast.DTO.Advertisement.UpdateAdvertisementRequest;
import com.example.dodast.Exception.ExceptionCreator;
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

        throw ExceptionCreator.createException(response);
    }

    public List<AdvertisementResponse> getMyAdvertisements() throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/me");

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), new TypeReference<List<AdvertisementResponse>>() {});
        }

        throw ExceptionCreator.createException(response);
    }

    public AdvertisementDetailResponse getAdvertisementDetail(Long advertisementId) throws Exception {


        HttpResponse<String> response = apiClient.get("/advertisements/" + advertisementId);

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), AdvertisementDetailResponse.class);
        }

        throw ExceptionCreator.createException(response);
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
            throw ExceptionCreator.createException(response);
        }
    }

    public AdvertisementResponse updateAdvertisement(Long advertisementId, UpdateAdvertisementRequest request) throws Exception {

        String requestBody = mapper.writeValueAsString(request);

        HttpResponse<String> response = apiClient.put("/advertisements/" + advertisementId, requestBody);

        if (!isSuccessful(response)) throw ExceptionCreator.createException(response);

        if (response.body() == null || response.body().isBlank()) return null;

        return mapper.readValue(response.body(), AdvertisementResponse.class);
    }

    public void deleteAdvertisement(Long advertisementId) throws Exception {

        HttpResponse<String> response = apiClient.delete("/advertisements/" + advertisementId);

        if (!isSuccessful(response)) throw ExceptionCreator.createException(response);
    }

    public void markAsSold(Long advertisementId) throws Exception {

        HttpResponse<String> response = apiClient.patch("/advertisements/" + advertisementId + "/sold");

        if (!isSuccessful(response)) throw ExceptionCreator.createException(response);
    }

    public void approveAdvertisement(Long advertisementId) throws Exception {

        HttpResponse<String> response = apiClient.patch("/advertisements/" + advertisementId + "/approve");

        if (!isSuccessful(response)) throw ExceptionCreator.createException(response);
    }

    public void rejectAdvertisement(Long advertisementId) throws Exception {

        HttpResponse<String> response = apiClient.patch("/advertisements/" + advertisementId + "/reject");

        if (!isSuccessful(response)) throw ExceptionCreator.createException(response);
    }

    public List<AdvertisementResponse> getPendingAdvertisements() throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/pending");

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), new TypeReference<List<AdvertisementResponse>>() {});
        }

        throw ExceptionCreator.createException(response);
    }

    public List<OptionResponse> getCategories() throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/categories");

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), new TypeReference<List<OptionResponse>>() {});
        }

        throw ExceptionCreator.createException(response);
    }

    public List<OptionResponse> getProvinces() throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/provinces");

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), new TypeReference<List<OptionResponse>>() {});
        }

        throw ExceptionCreator.createException(response);
    }

    public List<OptionResponse> getCities(Long provinceId) throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/cities/" + provinceId);

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), new TypeReference<List<OptionResponse>>() {});
        }

        throw ExceptionCreator.createException(response);
    }

    public OptionResponse getCategoryById(Long categoryId) throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/category/" + categoryId);

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), OptionResponse.class);
        }

        throw ExceptionCreator.createException(response);
    }

    public OptionResponse getProvinceById(Long provinceId) throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/province/" + provinceId);

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), OptionResponse.class);
        }

        throw ExceptionCreator.createException(response);
    }

    public OptionResponse getCityById(Long cityId) throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/city/" + cityId);

        if (isSuccessful(response)) {
            return mapper.readValue(response.body(), OptionResponse.class);
        }

        throw ExceptionCreator.createException(response);
    }

    private boolean isSuccessful(HttpResponse<String> response) {
        return response.statusCode() >= 200 && response.statusCode() < 300;
    }


}