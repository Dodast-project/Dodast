package com.example.dodast.Service;

import java.net.http.HttpResponse;
import java.util.List;

import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Exception.UIException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FavoriteService {

    private final ApiClient apiClient = new ApiClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void addFavorite(Long advertisementId) throws Exception {

        HttpResponse<String> response = apiClient.post("/favorite/" + advertisementId,"");

        if (response.statusCode() < 200 || response.statusCode() >= 300) throw new UIException(response.body(), response.statusCode());
    }

    public void removeFavorite(Long advertisementId) throws Exception {

        HttpResponse<String> response = apiClient.delete("/favorite/" + advertisementId);

        if (response.statusCode() < 200 || response.statusCode() >= 300) throw new UIException(response.body(), response.statusCode());
    }

    public List<AdvertisementResponse> getFavorites() throws Exception {

        HttpResponse<String> response = apiClient.get("/favorite");

        if (response.statusCode() < 200 || response.statusCode() >= 300) throw new UIException(response.body(), response.statusCode());

        return objectMapper.readValue(response.body(),new TypeReference<List<AdvertisementResponse>>() {});
    }
}