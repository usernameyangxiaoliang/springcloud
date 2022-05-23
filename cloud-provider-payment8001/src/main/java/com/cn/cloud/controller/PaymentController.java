package com.cn.cloud.controller;

import com.cn.cloud.common.entity.CommonResoult;
import com.cn.cloud.common.entity.Payment;
import com.cn.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    //服务发现，可以通过服务发现获得该服务的信息
    @Resource
    private DiscoveryClient discoveryClient;
    @PostMapping("/payment/create")
    public CommonResoult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果："+ result);
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
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*******element:"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"/t"+instance.getUri());
        }
        return  this.discoveryClient;
    }
    @RequestMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
    @RequestMapping("/payment/feign/timeout")
    public  String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
