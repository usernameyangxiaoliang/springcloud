package com.cn.cloud.controller;

import com.cn.cloud.common.entity.CommonResoult;
import com.cn.cloud.common.entity.Payment;
import com.cn.cloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResoult<Payment> getPayment(@PathVariable("id") Long id){
        return  paymentFeignService.get(id);
    }
    @RequestMapping("/consumer/payment/feign/timeout")
    public  String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }
}
