package com.atmuke.secpay.dto;

public class SecPayResult<T> {

    private boolean result;

    private T data;

    private String error;

    public SecPayResult(boolean result, T data) {
        this.result = result;
        this.data = data;
    }

    public SecPayResult(boolean result, String error) {
        this.result = result;
        this.error = error;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
