package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiException{

    public UserNotFoundException(){
        super("کاربر یافت نشد.", HttpStatus.NOT_FOUND);
    }
}
