package com.atmuke.secpay.dao;

import com.atmuke.secpay.entity.SecPay;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;

public interface SecPayDao {

    //减库存
    public int reduceNumber(@Param("secpayId") long secpayId, @Param("nowTime") Date nowTime);

    //根据ID查询
    public SecPay queryById(long secpayId);

    //查询所有 Param注解的作用是区分形参、因为java会编译成arg0 arg1
    public List<SecPay> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
