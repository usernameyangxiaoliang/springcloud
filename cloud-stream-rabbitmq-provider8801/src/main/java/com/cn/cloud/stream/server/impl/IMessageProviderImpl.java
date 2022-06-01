package com.cn.cloud.stream.server.impl;

import com.cn.cloud.stream.server.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)//定义消息推送管道
public class IMessageProviderImpl implements IMessageProvider {

    @Resource(name = "output")
    private MessageChannel messageChannel; //消息发送管道

    @Override
    public String send() {
        String msg = UUID.randomUUID().toString();
        messageChannel.send(MessageBuilder.withPayload(msg).build());
        System.out.println("****发送消息成功"+msg);
        return null;
    }
}
