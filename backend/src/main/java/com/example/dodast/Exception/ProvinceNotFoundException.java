package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class ProvinceNotFoundException extends ApiException {

    public ProvinceNotFoundException() {
        super("استان مورد نظر یافت نشد.", HttpStatus.NOT_FOUND);
    }

}
