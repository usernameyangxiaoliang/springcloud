package com.cn.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain8999 {
    public static void main(String[] args){
        SpringApplication.run(OrderFeignMain8999.class, args);
    }
}
