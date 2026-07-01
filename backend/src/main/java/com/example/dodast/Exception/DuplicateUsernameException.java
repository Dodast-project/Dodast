package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends ApiException{
    public DuplicateUsernameException(){
        super("Username already exists", HttpStatus.BAD_REQUEST);
    }
}
