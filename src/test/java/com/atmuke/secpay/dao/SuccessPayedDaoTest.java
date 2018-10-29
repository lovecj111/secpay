package com.atmuke.secpay.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.atmuke.secpay.entity.SuccessPayed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessPayedDaoTest {

    @Autowired
    private SuccessPayedDao successPayedDao;

    @Test
    public void insertSuccessPayed() {
        long secpayId = 1001;
        long userPhone = 13838838383L;
        int insertCount = successPayedDao.insertSuccessPayed(secpayId, userPhone);
        System.out.println(insertCount);
    }

    @Test
    public void queryByIdAndPhone() {
        long secpayId = 1001;
        long userPhone = 13838838383L;
        SuccessPayed successPayed = successPayedDao.queryByIdAndPhone(secpayId, userPhone);
        System.out.println(successPayed);
        System.out.println(successPayed.getSecPay());
    }
}