package com.example.dodast.Exception;


import org.springframework.http.HttpStatus;
import com.example.dodast.Exception.ApiException;

public class FavoriteNotFoundException extends ApiException{
    public FavoriteNotFoundException(){
        super("Favorite not found", HttpStatus.NOT_FOUND);
    }
}
