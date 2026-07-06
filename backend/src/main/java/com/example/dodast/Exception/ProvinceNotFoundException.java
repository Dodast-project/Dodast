package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class ProvinceNotFoundException extends ApiException {

    public ProvinceNotFoundException() {
        super("Province not found.", HttpStatus.NOT_FOUND);
    }

}
