package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class SelfMessageException extends ApiException {
    public SelfMessageException() {
        super("You cannot send a message to your own advertisement.", HttpStatus.BAD_REQUEST);
    }
}