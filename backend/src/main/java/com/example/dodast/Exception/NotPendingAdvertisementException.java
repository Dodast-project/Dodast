package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class NotPendingAdvertisementException extends ApiException{
    public NotPendingAdvertisementException(){
        super("وضعیت آگهی چیزی غیر از در انتظار تایید است", HttpStatus.BAD_REQUEST);
    }
}
