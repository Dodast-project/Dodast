package com.example.dodast.Service;

import com.example.dodast.DTO.Auth.AuthResponse;
import com.example.dodast.DTO.Auth.LoginRequest;
import com.example.dodast.DTO.Auth.RegisterRequest;
import com.example.dodast.Exception.ConnectionError;
import com.example.dodast.Exception.UIException;
import com.example.dodast.Util.SessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;

public class AuthService {

    private final ApiClient apiClient;
    private final ObjectMapper mapper;

    public AuthService() {
        apiClient = new ApiClient();
        mapper = new ObjectMapper();
    }

    public AuthResponse login(LoginRequest request) throws Exception {
        return authenticate("/api/auth/login", request);
    }

    public AuthResponse register(RegisterRequest request) throws Exception {
        return authenticate("/api/auth/register", request);
    }

    private AuthResponse authenticate(String path, Object request) throws Exception {

        String json = mapper.writeValueAsString(request);
        HttpResponse<String> response = apiClient.post(path, json);

        if (response.statusCode() >= 200 && response.statusCode() < 300) {

            AuthResponse authResponse = mapper.readValue(response.body(), AuthResponse.class);

            SessionManager.startSession(authResponse);
            return authResponse;
        }
        throw createApiException(response);
    }

    private UIException createApiException(HttpResponse<String> response) {

        try {
            UIException error = mapper.readValue(response.body(), UIException.class);

            return error;

        } catch (Exception ignored) {
            return new ConnectionError(response.statusCode());
        }
    }
}