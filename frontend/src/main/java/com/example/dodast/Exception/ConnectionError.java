package com.example.dodast.Exception;

public class ConnectionError extends UIException{
    public ConnectionError(int status){
        super("خطایی هنگام ارتباط با سرور رخ داد", status);
    }
}
