package com.cn.cloud.service;

import com.cn.cloud.common.entity.CommonResoult;
import com.cn.cloud.common.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    public CommonResoult get(@PathVariable("id") Long id);

    @PostMapping("/payment/create")
    public CommonResoult create(@RequestBody Payment payment);

    @RequestMapping("/payment/feign/timeout")
    public  String paymentFeignTimeout();
}
