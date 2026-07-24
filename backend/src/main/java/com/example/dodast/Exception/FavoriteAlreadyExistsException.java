package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;
import com.example.dodast.Exception.ApiException;

public class FavoriteAlreadyExistsException extends ApiException{
    public FavoriteAlreadyExistsException(){
        super("آگهی قبلا به علاقه مندی ها اضافه شده است.", HttpStatus.BAD_REQUEST);
    }
}
