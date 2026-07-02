package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends ApiException{
    public InvalidPasswordException(){
        super("Invalid password", HttpStatus.UNAUTHORIZED);
    }
}
