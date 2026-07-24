package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends ApiException{
    public DuplicateUsernameException(){
        super("نام کاربری قبلا ثبت شده است.", HttpStatus.BAD_REQUEST);
    }
}
