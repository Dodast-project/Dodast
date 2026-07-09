package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{
    private final String message;
    private HttpStatus status;

    public ApiException(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

    public String getMessage(){
        return message;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
