package com.atmuke.secpay.entity;

import java.util.Date;

public class SuccessPayed {

    //商品ID
    private long secpayId;
    //用户手机号
    private long userPhone;
    //状态 -1无效 0成功 1已付款 2已发货
    private short state;
    //创建时间
    private Date createTime;

    //多对一
    private SecPay secPay;

    public long getSecpayId() {
        return secpayId;
    }

    public void setSecpayId(long secpayId) {
        this.secpayId = secpayId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SecPay getSecPay() {
        return secPay;
    }

    public void setSecPay(SecPay secPay) {
        this.secPay = secPay;
    }

    @Override
    public String toString() {
        return "SuccessPayed{" +
                "secpayId=" + secpayId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", secPay=" + secPay +
                '}';
    }
}
