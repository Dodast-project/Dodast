package com.example.dodast.Exception;

public class UIException extends RuntimeException{
    private final String message;
    private final int status;
    public UIException(String message, int status){
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
