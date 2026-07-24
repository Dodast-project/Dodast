package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends ApiException {

    public CategoryNotFoundException() {
        super("دسته بندی یافت نشد.", HttpStatus.NOT_FOUND);
    }

}