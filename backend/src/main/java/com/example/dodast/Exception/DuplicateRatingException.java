package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class DuplicateRatingException extends ApiException {
    public DuplicateRatingException() {
        super("شما قبلا برای این آگهی به فروشنده امتیاز داده اید.", HttpStatus.CONFLICT);
    }
}
