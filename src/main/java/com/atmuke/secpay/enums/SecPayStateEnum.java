package com.atmuke.secpay.enums;

public enum SecPayStateEnum {

    SUCCESS(1,"秒成功"),
    END(0,"秒结束"),
    REPEAT(-1,"重复秒"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");

    private int state;

    private String stateInfo;

    private SecPayStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SecPayStateEnum stateOf(int state){
        for(SecPayStateEnum e : values()){
            if(e.getState() == state){
                return e;
            }
        }
        return null;
    }
}
