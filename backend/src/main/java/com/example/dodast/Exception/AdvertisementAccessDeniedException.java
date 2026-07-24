package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class AdvertisementAccessDeniedException extends ApiException {
    public AdvertisementAccessDeniedException() {
        super("شما اجازه تغییر این آگهی را ندارید.", HttpStatus.FORBIDDEN);
    }
}