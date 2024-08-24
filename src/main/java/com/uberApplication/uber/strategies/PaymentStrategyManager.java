package com.uberApplication.uber.strategies;

import org.springframework.stereotype.Component;

import com.uberApplication.uber.entities.enums.PaymentMethod;
import com.uberApplication.uber.strategies.impl.CashPaymentStrategy;
import com.uberApplication.uber.strategies.impl.WalletPaymentStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

       private final WalletPaymentStrategy walletPaymentStrategy;;
       private final CashPaymentStrategy cashPaymentStrategy;

       public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
               
        };
       }
    
    
}

