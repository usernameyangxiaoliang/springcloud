package com.cn.cloud.service;


import com.cn.cloud.common.entity.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
