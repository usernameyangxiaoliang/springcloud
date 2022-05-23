package com.cn.cloud.controller;

import com.cn.cloud.common.entity.CommonResoult;
import com.cn.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public CommonResoult get(@PathVariable("id") Integer id){
        String paymentInfoOk = paymentService.PaymentInfo_ok(id);
        log.info("*******查询结果："+serverPort+ paymentInfoOk);
        return new CommonResoult(200,"查询成功"+serverPort,paymentInfoOk);
    }

    @GetMapping("/payment/hystrix/error/{id}")
    public CommonResoult PaymentInfo_error(@PathVariable("id") Integer id){
        String paymentInfoError = paymentService.PaymentInfo_error(id);
        log.info("*******查询结果："+serverPort+ paymentInfoError);
        return new CommonResoult(400,"Error"+serverPort,paymentInfoError);
    }

    //服务熔断
    @GetMapping("/payment/hystrix/circuit/{id}")
    public CommonResoult PaymentCircuitBreaker(@PathVariable("id") Integer id){
        String paymentCircuitBreaker = paymentService.paymentCircuitBreaker(id);
        return new CommonResoult(400,"Error"+serverPort,paymentCircuitBreaker);
    }
}
