package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class ConversationAccessDeniedException extends ApiException {
    public ConversationAccessDeniedException() {
        super("شما به این گفت و گو دسترسی ندارید.", HttpStatus.FORBIDDEN);
    }
}
