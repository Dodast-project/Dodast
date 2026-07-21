package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class ConversationAccessDeniedException extends ApiException {
    public ConversationAccessDeniedException() {
        super("You do not have access to this conversation.", HttpStatus.FORBIDDEN);
    }
}
