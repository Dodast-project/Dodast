package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class InvalidJwtException extends ApiException{
    public InvalidJwtException(){
        super("Invalid or expired jwt token", HttpStatus.UNAUTHORIZED);
    }
}
