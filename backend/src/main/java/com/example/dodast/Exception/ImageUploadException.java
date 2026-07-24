package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class ImageUploadException extends ApiException{
    public ImageUploadException(){
        super("بارگذاری تصویر انجام نشد.",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
