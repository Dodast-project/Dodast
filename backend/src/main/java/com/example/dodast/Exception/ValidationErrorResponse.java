package com.example.dodast.Exception;

import java.util.Map;

public class ValidationErrorResponse {

    private String message;
    private int status;
    private Map<String, String> errors;

    public ValidationErrorResponse(String message, int status, Map<String, String> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
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