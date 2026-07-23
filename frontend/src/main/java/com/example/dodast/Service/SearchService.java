package com.example.dodast.Service;


import com.example.dodast.DTO.Advertisement.AdSearchRequest;
import com.example.dodast.DTO.Advertisement.AdvertisementResponse;
import com.example.dodast.Exception.ExceptionCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SearchService {

    private final ApiClient apiClient;
    private final ObjectMapper mapper;

    public SearchService(){
        apiClient = new ApiClient();
        mapper = new ObjectMapper();
    }

    public List<AdvertisementResponse> search(AdSearchRequest request) throws Exception {

        StringBuilder url = new StringBuilder("/search?");

        if(request.getKeyword()!=null)
            url.append("keyword=").append(URLEncoder.encode(request.getKeyword(), StandardCharsets.UTF_8)).append("&");

        if(request.getCategoryId()!=null)
            url.append("categoryId=").append(request.getCategoryId()).append("&");

        if(request.getProvinceId()!=null)
            url.append("provinceId=").append(request.getProvinceId()).append("&");

        if(request.getCityId()!=null)
            url.append("cityId=").append(request.getCityId()).append("&");

        if(request.getMinPrice()!=null)
            url.append("minPrice=").append(request.getMinPrice()).append("&");

        if(request.getMaxPrice()!=null)
            url.append("maxPrice=").append(request.getMaxPrice()).append("&");

        if(request.getSortBy()!=null)
            url.append("sortBy=").append(request.getSortBy());

        HttpResponse<String> response = apiClient.getWithQuery(url.toString());

        if(response.statusCode() >= 200 && response.statusCode() < 300){
            return mapper.readValue(response.body(), new TypeReference<List<AdvertisementResponse>>() {});
        }

        throw ExceptionCreator.createException(response);
    }


}