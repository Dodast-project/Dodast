package com.example.dodast.Exception;


import org.springframework.http.HttpStatus;
import com.example.dodast.Exception.ApiException;

public class FavoriteNotFoundException extends ApiException{
    public FavoriteNotFoundException(){
        super("علاقه مندی یافت نشد.", HttpStatus.NOT_FOUND);
    }
}
