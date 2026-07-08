package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class AdvertisementPermissionException extends ApiException {

    public AdvertisementPermissionException() {
        super("You are not allowed to modify this advertisement.", HttpStatus.NOT_FOUND);
    }

}
