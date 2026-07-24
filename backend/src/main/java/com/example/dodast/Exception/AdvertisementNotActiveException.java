package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class AdvertisementNotActiveException extends ApiException {

    public AdvertisementNotActiveException() {
        super("آگهی فعال نیست.", HttpStatus.NOT_FOUND);
    }

}