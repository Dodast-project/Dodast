package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class AdvertisementNotActiveException extends ApiException {

    public AdvertisementNotActiveException() {
        super("Advertisement is not active.", HttpStatus.NOT_FOUND);
    }

}