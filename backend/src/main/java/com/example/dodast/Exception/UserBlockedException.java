package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class UserBlockedException extends ApiException {
    public UserBlockedException() {
        super("این کاربر مسدود شده است و نمی تواند پیام ارسال یا دریافت کند.", HttpStatus.FORBIDDEN);
    }
}
