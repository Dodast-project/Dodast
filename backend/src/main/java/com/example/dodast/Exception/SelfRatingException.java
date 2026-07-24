package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class SelfRatingException extends ApiException {
    public SelfRatingException() {
        super("شما نمیتوانید به خودتان امتیاز دهید.", HttpStatus.BAD_REQUEST);
    }
}
