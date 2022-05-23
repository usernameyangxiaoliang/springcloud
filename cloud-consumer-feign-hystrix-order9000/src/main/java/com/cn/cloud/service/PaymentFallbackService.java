package com.cn.cloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String get(Integer id) {
        return "PaymentFallbackService-----fall back get";
    }

    @Override
    public String PaymentInfo_error(Integer id) {
        return "PaymentFallbackService------fall back PaymentInfo_error";
    }
}
