package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class CityNotFoundException extends ApiException {

    public CityNotFoundException() {
        super("City not found.", HttpStatus.NOT_FOUND);
    }

}