package com.example.dodast.Service;

import com.example.dodast.Util.SessionManager;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080";

    private final HttpClient client;

    public ApiClient() {
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
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

    public HttpResponse<String> delete(String path) throws Exception {

        HttpRequest request = baseRequest(path)
                .DELETE()
                .build();

        return send(request);
    }

    public HttpResponse<String> postAdvertisement(String title,
            String description,
            Long price,
            Long categoryId,
            Long provinceId,
            Long cityId,
            File image) throws Exception {

        String boundary = UUID.randomUUID().toString();

        List<HttpRequest.BodyPublisher> parts = new ArrayList<>();

        addTextPart(parts, boundary, "title", title);
        addTextPart(parts, boundary, "description", description);
        addTextPart(parts, boundary, "price", String.valueOf(price));
        addTextPart(parts, boundary, "categoryId", String.valueOf(categoryId));
        addTextPart(parts, boundary, "provinceId", String.valueOf(provinceId));
        addTextPart(parts, boundary, "cityId", String.valueOf(cityId));

        if (image != null) {
            addFilePart(parts, boundary, image);
        }

        parts.add(HttpRequest.BodyPublishers.ofString("--" + boundary + "--\r\n"));

        HttpRequest request = baseRequest("/advertisements")
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .POST(HttpRequest.BodyPublishers.concat(parts.toArray(new HttpRequest.BodyPublisher[0])))
                .build();

        return send(request);
    }

    public HttpResponse<String> put(String path, String jsonBody) throws Exception {

        HttpRequest request = baseRequest(path)
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        return send(request);
    }

    public HttpResponse<String> patch(String path)throws Exception {

        HttpRequest request = baseRequest(path)
                .method("PATCH", HttpRequest.BodyPublishers.noBody())
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

    private void addTextPart(List<HttpRequest.BodyPublisher> parts,
            String boundary,
            String name,
            String value) {

        String part =
                "--" + boundary + "\r\n"
                + "Content-Disposition: form-data; name=\""
                + name
                + "\"\r\n\r\n"
                + value
                + "\r\n";

        parts.add(HttpRequest.BodyPublishers.ofString(part));
    }

    private void addFilePart(List<HttpRequest.BodyPublisher> parts,
            String boundary,
            File image) throws Exception {

        String contentType = Files.probeContentType(image.toPath());

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        String fileHeader =
                "--" + boundary + "\r\n"
                + "Content-Disposition: form-data; "
                + "name=\"images\"; "
                + "filename=\"" + image.getName() + "\"\r\n"
                + "Content-Type: " + contentType + "\r\n\r\n";

        parts.add(HttpRequest.BodyPublishers.ofString(fileHeader));

        parts.add(HttpRequest.BodyPublishers.ofFile(image.toPath()));

        parts.add(HttpRequest.BodyPublishers.ofString("\r\n"));
    }
}