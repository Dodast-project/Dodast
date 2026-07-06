package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends ApiException {

    public CategoryNotFoundException() {
        super("Category not found.", HttpStatus.NOT_FOUND);
    }

}