package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class AdvertisementNotFoundException extends ApiException {
    public AdvertisementNotFoundException() {
        super("Advertisement not found.", HttpStatus.NOT_FOUND);
    }
}
