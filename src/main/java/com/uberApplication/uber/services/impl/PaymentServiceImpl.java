package com.uberApplication.uber.services.impl;

import org.springframework.stereotype.Service;

import com.uberApplication.uber.entities.Payment;
import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.enums.PaymentStatus;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.repository.PaymentRepo;
import com.uberApplication.uber.services.PaymentService;
import com.uberApplication.uber.strategies.PaymentStrategyManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final PaymentStrategyManager paymentStrategyManager;
    
    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepo.findByRide(ride).orElseThrow(()->new ResourceNotFoundException("payment not found with ride:"+ride.getId()));
      paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
       Payment payment = Payment.builder()
                     .ride(ride)
                     .amount(ride.getFare())
                     .paymentMethod(ride.getPaymentMethod())
                     .paymentStatus(PaymentStatus.PENDING)
                     .build();

                    return paymentRepo.save(payment);
    }

    @Override
    public void updatePaymentService(Payment payment, PaymentStatus paymentStatus) {
     payment.setPaymentStatus(paymentStatus);
     paymentRepo.save(payment);
    }
    
}
