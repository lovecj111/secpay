package com.atmuke.secpay.dto;

import com.atmuke.secpay.entity.SuccessPayed;
import com.atmuke.secpay.enums.SecPayStateEnum;

public class SecPayExecution {
    //商品ID
    private long secpayId;
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //秒成功的对象
    private SuccessPayed successPayed;

    public SecPayExecution(long secpayId, SecPayStateEnum stateEnum, SuccessPayed successPayed) {
        this.secpayId = secpayId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successPayed = successPayed;
    }

    public SecPayExecution(long secpayId, SecPayStateEnum stateEnum) {
        this.secpayId = secpayId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getSecpayId() {
        return secpayId;
    }

    public void setSecpayId(long secpayId) {
        this.secpayId = secpayId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessPayed getSuccessPayed() {
        return successPayed;
    }

    public void setSuccessPayed(SuccessPayed successPayed) {
        this.successPayed = successPayed;
    }

    @Override
    public String toString() {
        return "SecPayExecution{" +
                "secpayId=" + secpayId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successPayed=" + successPayed +
                '}';
    }
}
