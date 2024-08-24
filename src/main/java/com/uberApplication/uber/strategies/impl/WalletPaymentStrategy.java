package com.uberApplication.uber.strategies.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uberApplication.uber.entities.Driver;
import com.uberApplication.uber.entities.Payment;
import com.uberApplication.uber.entities.Rider;
import com.uberApplication.uber.entities.enums.PaymentStatus;
import com.uberApplication.uber.entities.enums.TransactionMethod;
import com.uberApplication.uber.repository.PaymentRepo;
import com.uberApplication.uber.services.WalletService;
import com.uberApplication.uber.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

// let's make logic for wallet payment
// assume rider wallet has 100 rupees
// driver has 500
// deduct 50 rupees from rider wallet that iss payment for rider wallet has 50 rupees
// add 70 percent of 50 in driver wallet as his payment and remain 30 percent is or commission


@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    
           private final WalletService walletService;
           private final PaymentRepo paymentRepo;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
     Driver driver = payment.getRide().getDriver();
     Rider rider = payment.getRide().getRider();

     walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null,payment.getRide(),TransactionMethod.RIDE);

     double DriversCut = payment.getAmount() * (1-PLATFORM_COMMISSION);

     walletService.addMoneyToWallet(driver.getUser(), DriversCut, null, payment.getRide(), TransactionMethod.RIDE);

     payment.setPaymentStatus(PaymentStatus.CONFIRMED);
     paymentRepo.save(payment);
    }
    
}
