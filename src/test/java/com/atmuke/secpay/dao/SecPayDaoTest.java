package com.atmuke.secpay.dao;

import com.atmuke.secpay.entity.SecPay;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SecPayDaoTest {

    @Autowired
    private SecPayDao secPayDao;

    @Test
    public void reduceNumber() {
        long secpayId = 1001;
        Date nowTime = new Date();
        int updateCount = secPayDao.reduceNumber(secpayId, nowTime);
        System.out.println(updateCount);
    }

    @Test
    public void queryById() {
        long secpayId = 1001;
        SecPay secPay = secPayDao.queryById(secpayId);
        System.out.println(secPay);
    }

    @Test
    public void queryAll() {
        List<SecPay> secPays = secPayDao.queryAll(0, 100);
        for(SecPay secPay : secPays){
            System.out.println(secPay);
        }
    }
}