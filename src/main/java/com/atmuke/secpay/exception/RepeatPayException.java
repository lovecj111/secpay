package com.atmuke.secpay.exception;

//重复秒异常
public class RepeatPayException extends SecPayException{

    public RepeatPayException(String message) {
        super(message);
    }

    public RepeatPayException(String message, Throwable cause) {
        super(message, cause);
    }
}
