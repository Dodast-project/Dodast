package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class InvalidPriceRangeException extends ApiException{
    public InvalidPriceRangeException(){
        super("محدوده قیمت نامعتبر است.", HttpStatus.BAD_REQUEST);
    }
}
