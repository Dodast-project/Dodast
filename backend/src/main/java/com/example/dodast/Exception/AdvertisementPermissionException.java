package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class AdvertisementPermissionException extends ApiException {

    public AdvertisementPermissionException() {
        super("شما اجازه تغییر این آگهی را ندارید.", HttpStatus.NOT_FOUND);
    }

}
