package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class AdvertisementNotFoundException extends ApiException {
    public AdvertisementNotFoundException() {
        super("آگهی یافت نشد.", HttpStatus.NOT_FOUND);
    }
}
