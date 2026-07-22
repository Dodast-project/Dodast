package com.example.dodast.Exception;

import java.net.http.HttpResponse;

public class ExceptionCreator {
    public static RuntimeException createException(HttpResponse<String> response){
        try {
            ErrorResponse error = new ErrorResponse();
            error = error.map(response);
            return new RuntimeException(error.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return new RuntimeException("خطایی در برقراری ارتباط با سرور پیش آمد");
        }
    }
}
