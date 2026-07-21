package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class DuplicateRatingException extends ApiException {
    public DuplicateRatingException() {
        super("You have already rated the seller for this advertisement.", HttpStatus.CONFLICT);
    }
}
