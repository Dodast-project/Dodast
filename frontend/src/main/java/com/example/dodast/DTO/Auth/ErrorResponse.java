package com.example.dodast.DTO.Auth;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    private String message;
    private int status;
    private Map<String, String> errors;

    public ErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}