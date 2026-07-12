package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class AdvertisementAccessDeniedException extends ApiException {
    public AdvertisementAccessDeniedException() {
        super("You are not allowed to modify this advertisement", HttpStatus.FORBIDDEN);
    }
}