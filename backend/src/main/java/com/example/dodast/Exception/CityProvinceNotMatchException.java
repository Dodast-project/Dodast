package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class CityProvinceNotMatchException extends ApiException{
    public CityProvinceNotMatchException(){
        super("City is not in the selected province", HttpStatus.BAD_REQUEST);
    }
}
