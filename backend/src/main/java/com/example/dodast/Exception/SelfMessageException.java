package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class SelfMessageException extends ApiException {
    public SelfMessageException() {
        super("شما نمیتوانید برای آگهی خودتان پیام ارسال کنید.", HttpStatus.BAD_REQUEST);
    }
}