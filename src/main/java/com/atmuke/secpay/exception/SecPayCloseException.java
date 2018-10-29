package com.atmuke.secpay.exception;

//秒关闭异常
public class SecPayCloseException extends SecPayException{

    public SecPayCloseException(String message) {
        super(message);
    }

    public SecPayCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
