package com.cn.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope//刷新
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;
    //测试从远程github上读取配置信息
    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
