package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class ImageUploadException extends ApiException{
    public ImageUploadException(){
        super("Failed to upload image",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
