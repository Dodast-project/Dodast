package com.example.dodast.Service;

import com.example.dodast.Util.SessionManager;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080";

    private final HttpClient client;

    public ApiClient() {
        this.client = HttpClient.newHttpClient();
    }

    public HttpResponse<String> get(String path) throws Exception {
        HttpRequest.Builder builder = baseRequest(path).GET();
        return send(builder.build());
    }

    public HttpResponse<String> post(String path, String jsonBody) throws Exception {
        HttpRequest request = baseRequest(path)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return send(request);
    }

    private HttpRequest.Builder baseRequest(String path) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .header("Accept", "application/json");

        if (SessionManager.isLoggedIn()) {
            builder.header("Authorization", "Bearer " + SessionManager.getToken());
        }

        return builder;
    }

    private HttpResponse<String> send(HttpRequest request) throws Exception {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}