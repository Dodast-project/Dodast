package com.example.dodast.Exception;

public class GetAdvertisementError extends UIException{
    public GetAdvertisementError(int status){
        super("خطایی در بارگذاری آگهی ها رخ داد", status);
    }
}
