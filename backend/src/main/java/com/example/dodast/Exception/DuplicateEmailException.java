package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ApiException{
    public DuplicateEmailException(){
        super("ایمیل قبلا ثبت شده است.", HttpStatus.BAD_REQUEST);
    }
}
