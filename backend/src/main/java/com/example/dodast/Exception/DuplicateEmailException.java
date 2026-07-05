package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ApiException{
    public DuplicateEmailException(){
        super("Email already exists", HttpStatus.BAD_REQUEST);
    }
}
