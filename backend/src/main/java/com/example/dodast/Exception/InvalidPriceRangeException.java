package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class InvalidPriceRangeException extends ApiException{
    public InvalidPriceRangeException(){
        super("Invalid price range", HttpStatus.BAD_REQUEST);
    }
}
