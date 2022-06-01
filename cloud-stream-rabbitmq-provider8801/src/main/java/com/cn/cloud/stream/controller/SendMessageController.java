package com.cn.cloud.stream.controller;

import com.cn.cloud.stream.server.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {
    @Autowired
    private IMessageProvider iMessageProvider;
    @RequestMapping("/sedMsg")
    public String sendMsg(){
        return iMessageProvider.send();
    }
}
