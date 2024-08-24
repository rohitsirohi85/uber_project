package com.uberApplication.uber.strategies;

import com.uberApplication.uber.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION=0.3; // for 30 percent commission
    void processPayment(Payment payment);
}
