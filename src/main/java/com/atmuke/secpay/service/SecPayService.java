package com.atmuke.secpay.service;

import com.atmuke.secpay.dto.Exposer;
import com.atmuke.secpay.dto.SecPayExecution;
import com.atmuke.secpay.entity.SecPay;
import com.atmuke.secpay.exception.RepeatPayException;
import com.atmuke.secpay.exception.SecPayCloseException;
import com.atmuke.secpay.exception.SecPayException;
import java.util.List;

public interface SecPayService {

    //查询所有秒的商品
    public List<SecPay> getSecPayList();

    //查询单个秒的商品
    public SecPay getSecPay(long secpayId);

    //输出秒的地址、否则输出系统时间和秒的时间
    public Exposer exportSecPayUrl(long secpayId);

    //执行秒操作
    public SecPayExecution executeSecPay(long secpayId, long userPhone, String md5) throws SecPayException,
            SecPayCloseException, RepeatPayException;

}
