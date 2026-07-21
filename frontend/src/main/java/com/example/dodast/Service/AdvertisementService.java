package com.example.dodast.Service;

import com.example.dodast.DTO.Advertisement.AdvertisementDetailResponse;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
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

    public List<AdvertisementResponse> getActiveAdvertisements() throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements");

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return mapper.readValue(response.body(), new TypeReference<List<AdvertisementResponse>>() {});
        }

        throw new GetAdvertisementError(response.statusCode());
    }

    public HttpResponse<String>  createAdvertisement(String title,
            String description,
            String price,
            String categoryId,
            String provinceId,
            String cityId,
            File image) throws Exception {

        HttpResponse<String> response = apiClient.postAdvertisement(
                        title,
                        description,
                        price,
                        categoryId,
                        provinceId,
                        cityId,
                        image
                    );
        if(response.statusCode() >= 200 && response.statusCode() < 300) return null;

        return response;
    }

    public AdvertisementDetailResponse getAdvertisementDetail(Long advertisementId)throws Exception {

        HttpResponse<String> response = apiClient.get("/advertisements/" + advertisementId);

        if (response.statusCode() >= 200 && response.statusCode() < 300) {

            return mapper.readValue(response.body(), AdvertisementDetailResponse.class);
        }

        throw new UIException(response.body(), response.statusCode());
    }
}