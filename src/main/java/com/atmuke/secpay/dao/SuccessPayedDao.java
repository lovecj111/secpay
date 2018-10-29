package com.atmuke.secpay.dao;

import com.atmuke.secpay.entity.SuccessPayed;
import org.apache.ibatis.annotations.Param;

public interface SuccessPayedDao {

    //新增购买明细、可过滤重复
    public int insertSuccessPayed(@Param("secpayId") long secpayId, @Param("userPhone") long userPhone);

    //根据ID查询
    public SuccessPayed queryByIdAndPhone(@Param("secpayId") long secpayId, @Param("userPhone") long userPhone);
}
