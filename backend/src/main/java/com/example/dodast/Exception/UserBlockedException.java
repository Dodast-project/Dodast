package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class UserBlockedException extends ApiException {
    public UserBlockedException() {
        super("This user is blocked and cannot send or receive messages.", HttpStatus.FORBIDDEN);
    }
}
