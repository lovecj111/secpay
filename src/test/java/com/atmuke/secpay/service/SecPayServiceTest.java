package com.atmuke.secpay.service;

import com.atmuke.secpay.dto.Exposer;
import com.atmuke.secpay.dto.SecPayExecution;
import com.atmuke.secpay.entity.SecPay;
import com.atmuke.secpay.exception.RepeatPayException;
import com.atmuke.secpay.exception.SecPayCloseException;
import com.atmuke.secpay.exception.SecPayException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SecPayServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecPayService secPayService;

    @Test
    public void getSecPayList() {
        List<SecPay> secPayList = secPayService.getSecPayList();
        logger.info("list={}", secPayList);
    }

    @Test
    public void getSecPay() {
        long secpayId = 1001;
        SecPay secPay = secPayService.getSecPay(secpayId);
        logger.info("secPay={}", secPay);
    }

    @Test
    public void exportSecPayUrl() {
        long secpayId = 1001;
        Exposer exposer = secPayService.exportSecPayUrl(secpayId);
        logger.info("exposer={}", exposer);
    }

    @Test
    public void executeSecPay() {
        long secpayId = 1001;
        long userPhone = 13839887962L;
        String md5 = "8b31e30f8742d6389874f3efe962be7a";
        try {
            SecPayExecution execution = secPayService.executeSecPay(secpayId, userPhone, md5);
            logger.info("execution={}", execution);
        } catch (RepeatPayException e) {
            logger.error(e.getMessage());
        } catch (SecPayCloseException e){
            logger.error(e.getMessage());
        }
    }
}