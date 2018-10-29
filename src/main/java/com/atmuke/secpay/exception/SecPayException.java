package com.atmuke.secpay.exception;

//秒相关业务异常
public class SecPayException  extends RuntimeException{

    public SecPayException(String message) {
        super(message);
    }

    public SecPayException(String message, Throwable cause) {
        super(message, cause);
    }
}
