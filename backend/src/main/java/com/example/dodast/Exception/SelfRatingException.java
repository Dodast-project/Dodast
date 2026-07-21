package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class SelfRatingException extends ApiException {
    public SelfRatingException() {
        super("You cannot rate yourself.", HttpStatus.BAD_REQUEST);
    }
}
