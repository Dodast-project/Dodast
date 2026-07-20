package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class ConversationNotFoundException extends ApiException {
    public ConversationNotFoundException() {
        super("Conversation not found.", HttpStatus.NOT_FOUND);
    }
}
