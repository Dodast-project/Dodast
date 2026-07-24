package com.example.dodast.Exception;

import org.springframework.http.HttpStatus;

public class CityProvinceNotMatchException extends ApiException{
    public CityProvinceNotMatchException(){
        super("شهر مورد نظر در استان انتخاب شده قرار ندارد.", HttpStatus.BAD_REQUEST);
    }
}
