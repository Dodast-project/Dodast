package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;
import com.example.dodast.Exception.ApiException;

public class FavoriteAlreadyExistsException extends ApiException{
    public FavoriteAlreadyExistsException(){
        super("Advertisement is already in favorites", HttpStatus.BAD_REQUEST);
    }
}
