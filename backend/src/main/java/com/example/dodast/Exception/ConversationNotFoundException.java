package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class ConversationNotFoundException extends ApiException {
    public ConversationNotFoundException() {
        super("گفت و گو یافت نشد.", HttpStatus.NOT_FOUND);
    }
}
