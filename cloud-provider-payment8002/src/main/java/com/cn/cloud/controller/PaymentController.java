package com.cn.cloud.controller;

import com.cn.cloud.common.entity.CommonResoult;
import com.cn.cloud.common.entity.Payment;
import com.cn.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/payment/create")
    public CommonResoult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果："+serverPort+ result);
        if(result > 0){
            return new CommonResoult(200,"插入数据库成功"+serverPort,result);
        }else {
            return new CommonResoult(444,"插入数据库失败"+serverPort,null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResoult get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******查询结果："+serverPort+ payment);
        if(payment !=null){
            return new CommonResoult(200,"查询成功"+serverPort,payment);
        }else {
            return new CommonResoult(444,serverPort+"没有对应的记录，查询ID："+id,null);
        }
    }

    @RequestMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
