package com.uberApplication.uber.strategies.impl;

import org.springframework.stereotype.Service;

import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Payment;
import com.uberApplication.uber.entities.enums.PaymentStatus;
import com.uberApplication.uber.entities.enums.TransactionMethod;
import com.uberApplication.uber.repository.PaymentRepo;
import com.uberApplication.uber.services.WalletService;
import com.uberApplication.uber.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

// make cashPayment logic here
// rider -> 100 give driver as payment
// deduct 30 percent from  driver wallet as our commission and remain is driver salary



@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

private final WalletService walletService;
private final PaymentRepo paymentRepo;

    @Override
    public void processPayment(Payment payment) {
       Driver driver = payment.getRide().getDriver();
     
       double platformCommission = payment.getAmount()*PLATFORM_COMMISSION;

       walletService.deductMoneyFromWallet(driver.getUser(), platformCommission , null , payment.getRide() , TransactionMethod.RIDE);

       payment.setPaymentStatus(PaymentStatus.CONFIRMED);
       paymentRepo.save(payment);
    }
    
}
