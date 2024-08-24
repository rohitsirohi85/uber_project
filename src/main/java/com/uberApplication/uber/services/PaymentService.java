package com.uberApplication.uber.services;

import com.uberApplication.uber.entities.Payment;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);
    Payment createNewPayment(Ride ride);
    void updatePaymentService(Payment payment , PaymentStatus paymentStatus);
}
