package com.cn.cloud.controller;

import com.cn.cloud.common.entity.CommonResoult;
import com.cn.cloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")//全局的fallback，下面方法没有指定的就使用全局的
public class OrderHystirxController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public CommonResoult get(@PathVariable("id") Integer id){
        String paymentInfoOk = paymentHystrixService.get(id);
        log.info("*******查询结果："+serverPort+ paymentInfoOk);
        return new CommonResoult(200,"查询成功"+serverPort,paymentInfoOk);
    }

    /***
     * @HystrixCommand:是调用服务方法失败抛出错误信息后会自动调用HystrixCommand
     * 标注好的fallbackMethod调用类中指定方法
     * @param id
     * @return
     * @HystrixProperty：设置自身调用超时时间的峰值，峰值内可以正常运行，
     * 超过了需要有兜底的方法处理，作为服务降级fallback
     */

    @GetMapping("/consumer/payment/hystrix/error/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallbackMethod",commandProperties = {
////            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
////    })
    //@HystrixCommand
    public CommonResoult PaymentInfo_error(@PathVariable("id") Integer id){
        String paymentInfoError = paymentHystrixService.PaymentInfo_error(id);
        log.info("*******查询结果："+serverPort+ paymentInfoError);
        return new CommonResoult(400,"Error"+serverPort,paymentInfoError);
    }
//    public CommonResoult paymentInfo_TimeOutFallbackMethod(@PathVariable("id") Integer id){
//        return new CommonResoult(400,"Error"+serverPort+"线程池"+Thread.currentThread().getName()+" 我是9000客户端服务，请按照规定返回值，请稍后,Id:"+id+"进入兜底方案",null);
//    }
//    //下面是全局fallback
//    public CommonResoult payment_Global_FallbackMethod(){
//        return new CommonResoult(400,"Error"+serverPort+"Global异常处理信息，请稍后再试！");
//    }

}
