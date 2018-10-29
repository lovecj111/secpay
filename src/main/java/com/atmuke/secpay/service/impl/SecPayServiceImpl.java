package com.atmuke.secpay.service.impl;

import com.atmuke.secpay.dao.SecPayDao;
import com.atmuke.secpay.dao.SuccessPayedDao;
import com.atmuke.secpay.dto.Exposer;
import com.atmuke.secpay.dto.SecPayExecution;
import com.atmuke.secpay.entity.SecPay;
import com.atmuke.secpay.entity.SuccessPayed;
import com.atmuke.secpay.enums.SecPayStateEnum;
import com.atmuke.secpay.exception.RepeatPayException;
import com.atmuke.secpay.exception.SecPayCloseException;
import com.atmuke.secpay.exception.SecPayException;
import com.atmuke.secpay.service.SecPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SecPayServiceImpl implements SecPayService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SecPayDao secPayDao;
    @Resource
    private SuccessPayedDao successPayedDao;
    //md5盐值字符串、用于混淆md5
    private final String slat = "wasasdasdwasdasdw!@!#@@!123";

    public List<SecPay> getSecPayList() {
        return secPayDao.queryAll(0,10);
    }

    public SecPay getSecPay(long secpayId) {
        return secPayDao.queryById(secpayId);
    }

    public Exposer exportSecPayUrl(long secpayId) {
        SecPay secPay = secPayDao.queryById(secpayId);
        if(secPay == null){
            return new Exposer(false, secpayId);
        }
        Date startTime = secPay.getStartTime();
        Date endTime = secPay.getEndTime();
        Date now = new Date();
        if(now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()){
            return new Exposer(false, secpayId, now.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(secpayId);
        return new Exposer(true, md5, secpayId);
    }

    private String getMD5(long secpayId){
        String base = secpayId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    public SecPayExecution executeSecPay(long secpayId, long userPhone, String md5) throws SecPayException, SecPayCloseException, RepeatPayException {
        if(md5 == null || !md5.equals(getMD5(secpayId))){
            throw new SecPayException("md5验证有问题");
        }
        try {
            //执行秒逻辑、减库存、记录购买行为
            Date nowTime = new Date();
            int updateCount = secPayDao.reduceNumber(secpayId, nowTime);
            if(updateCount <= 0){
                throw new SecPayCloseException("秒已经结束了");
            }
            int insertCount = successPayedDao.insertSuccessPayed(secpayId, userPhone);
            if(insertCount <= 0){
                throw new RepeatPayException("重复秒");
            }
            SuccessPayed successPayed = successPayedDao.queryByIdAndPhone(secpayId, userPhone);
            return new SecPayExecution(secpayId, SecPayStateEnum.SUCCESS, successPayed);
        } catch (SecPayCloseException e) {
            throw e;
        } catch (RepeatPayException e) {
            throw e;
        } catch (Exception e){
            throw new SecPayException(e.getMessage());
        }
    }
}
