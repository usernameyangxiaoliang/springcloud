package com.cn.cloud.test;

import java.time.ZonedDateTime;

public class Test {
    public static void main(String[] args){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();//默认时区
        System.out.println(zonedDateTime);
    }
}
