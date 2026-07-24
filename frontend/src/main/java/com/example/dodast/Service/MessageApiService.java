package com.example.dodast.Service;

import com.example.dodast.DTO.Message.ConversationResponse;
import com.example.dodast.DTO.Message.MessageResponse;
import com.example.dodast.DTO.Message.SendMessageRequest;
import com.example.dodast.Exception.ExceptionCreator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.http.HttpResponse;
import java.util.List;

public class MessageApiService {

    private final ApiClient apiClient;
    private final ObjectMapper mapper;

    public MessageApiService() {
        apiClient = new ApiClient();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public List<ConversationResponse> getConversations() throws Exception {
        HttpResponse<String> response = apiClient.get("/messages/conversations");

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return mapper.readValue(
                    response.body(),
                    mapper.getTypeFactory().constructCollectionType(List.class, ConversationResponse.class)
            );
        }
        throw ExceptionCreator.createException(response);
    }

    public List<MessageResponse> getMessages(Long conversationId) throws Exception {
        HttpResponse<String> response = apiClient.get("/messages/conversations/" + conversationId);

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return mapper.readValue(
                    response.body(),
                    mapper.getTypeFactory().constructCollectionType(List.class, MessageResponse.class)
            );
        }
        throw ExceptionCreator.createException(response);
    }

    public MessageResponse sendMessage(Long advertisementId, String text) throws Exception {
        SendMessageRequest request = new SendMessageRequest();
        request.setAdvertisementId(advertisementId);
        request.setText(text);

        String json = mapper.writeValueAsString(request);
        HttpResponse<String> response = apiClient.post("/messages", json);

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return mapper.readValue(response.body(), MessageResponse.class);
        }
        throw ExceptionCreator.createException(response);
    }

}
