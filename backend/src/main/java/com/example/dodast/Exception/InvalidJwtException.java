package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class InvalidJwtException extends ApiException{
    public InvalidJwtException(){
        super("توکن JWT نامعتبر یا منقضی شده است.", HttpStatus.UNAUTHORIZED);
    }
}
