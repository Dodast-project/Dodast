package com.example.dodast.Exception;

import java.net.http.HttpResponse;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    private String message;
    private int status;
    private Map<String, String> errors;
    ObjectMapper objectMapper = new ObjectMapper();

    public ErrorResponse() {}

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getErrorsString(){
        String errorString = "";
        for(String error: errors.keySet()){
            errorString += error + ": " + errors.get(error) + "\n";
        }
        return errorString;
    }

    @Override
    public String toString() {
        return message + " " + status + "\n" +(errors == null ? "" : getErrorsString());
    }

    public ErrorResponse map(HttpResponse<String> response) throws Exception{
        return objectMapper.readValue(response.body(), ErrorResponse.class);
    }
}