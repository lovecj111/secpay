package com.atmuke.secpay.dto;

public class Exposer {
    //是否开启
    private boolean exposed;
    //加密方式
    private String md5;
    //商品ID
    private long secpayId;
    //系统当前时间(毫秒)
    private long now;
    //开始时间
    private long start;
    //结束时间
    private long end;

    public Exposer(boolean exposed, long secpayId) {
        this.exposed = exposed;
        this.secpayId = secpayId;
    }

    public Exposer(boolean exposed, String md5, long secpayId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.secpayId = secpayId;
    }

    public Exposer(boolean exposed, long secpayId, long now, long start, long end) {
        this.exposed = exposed;
        this.secpayId = secpayId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSecpayId() {
        return secpayId;
    }

    public void setSecpayId(long secpayId) {
        this.secpayId = secpayId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", secpayId=" + secpayId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
