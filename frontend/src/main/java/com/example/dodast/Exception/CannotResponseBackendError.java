package com.example.dodast.Exception;

public class CannotResponseBackendError extends UIException{
    public CannotResponseBackendError(int status){
        super("در گرفتن خطا از سرور خطایی پیش آمده", status);
    }
}
