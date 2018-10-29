package com.atmuke.secpay.web;

import com.atmuke.secpay.dto.Exposer;
import com.atmuke.secpay.dto.SecPayExecution;
import com.atmuke.secpay.dto.SecPayResult;
import com.atmuke.secpay.entity.SecPay;
import com.atmuke.secpay.enums.SecPayStateEnum;
import com.atmuke.secpay.exception.RepeatPayException;
import com.atmuke.secpay.exception.SecPayCloseException;
import com.atmuke.secpay.exception.SecPayException;
import com.atmuke.secpay.service.SecPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("secpay")
public class SecPayController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecPayService secPayService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        List<SecPay> list = secPayService.getSecPayList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{secpayId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("secpayId") Long secpayId, Model model){
        if(secpayId == null){
            return "redirect:/secpay/list";
        }
        SecPay secPay = secPayService.getSecPay(secpayId);
        if(secPay == null){
            return "redirect:/secpay/list";
        }
        model.addAttribute("secPay", secPay);
        return "detail";
    }

    @RequestMapping(value = "/{secpayId}/exposer", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecPayResult<Exposer> exposer(@PathVariable("secpayId") Long secpayId){
        SecPayResult<Exposer> result;
            try {
            Exposer exposer = secPayService.exportSecPayUrl(secpayId);
            result = new SecPayResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SecPayResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{secpayId}/{md5}/execute", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecPayResult<SecPayExecution> execute(@PathVariable("secpayId") Long secpayId,
                                                 @PathVariable("md5") String md5,
                                                 @CookieValue(value = "payPhone", required = false) Long payPhone){
        if(payPhone == null){
            return new SecPayResult<SecPayExecution>(false,"未注册");
        }
        SecPayResult<SecPayExecution> result;
        try {
            SecPayExecution execution = secPayService.executeSecPay(secpayId, payPhone, md5);
            return new SecPayResult<SecPayExecution>(true, execution);
        } catch (RepeatPayException e){
            SecPayExecution execution = new SecPayExecution(secpayId, SecPayStateEnum.REPEAT);
            return new SecPayResult<SecPayExecution>(true, execution);
        } catch (SecPayCloseException e){
            SecPayExecution execution = new SecPayExecution(secpayId, SecPayStateEnum.END);
            return new SecPayResult<SecPayExecution>(true, execution);
        } catch (SecPayException e) {
            logger.error(e.getMessage(), e);
            return new SecPayResult<SecPayExecution>(true, e.getMessage());
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SecPayResult<Long> time(){
        Date now = new Date();
        return new SecPayResult<Long>(true, now.getTime());
    }

}
