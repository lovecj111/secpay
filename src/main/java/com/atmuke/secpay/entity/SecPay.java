package com.atmuke.secpay.entity;

import java.util.Date;

public class SecPay {

    //商品ID
    private long secpayId;
    //商品名称
    private String name;
    //库存数量
    private int number;
    //秒的开始时间
    private Date startTime;
    //秒的结束时间
    private Date endTime;
    //创建时间
    private Date createTime;

    public long getSecpayId() {
        return secpayId;
    }

    public void setSecpayId(long secpayId) {
        this.secpayId = secpayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SecPay{" +
                "secpayId=" + secpayId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
