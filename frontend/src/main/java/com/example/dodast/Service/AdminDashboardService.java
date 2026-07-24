package com.example.dodast.Service;

import com.example.dodast.DTO.Admin.AdminDashboardResponse;
import com.example.dodast.Exception.ExceptionCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;

public class AdminDashboardService {

    private final ApiClient apiClient;
    private final ObjectMapper objectMapper;

    public AdminDashboardService() {
        this.apiClient = new ApiClient();
        this.objectMapper = new ObjectMapper();
    }

    public AdminDashboardResponse getStatistics() throws Exception {

        HttpResponse<String> response = apiClient.get("/admin/dashboard");

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw ExceptionCreator.createException(response);
        }

        return objectMapper.readValue(response.body(), AdminDashboardResponse.class);
    }
}