package com.uberApplication.uber.services;

import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.User;
import com.uberApplication.uber.entities.Wallet;
import com.uberApplication.uber.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount , String transactionId , Ride ride ,TransactionMethod transactionMethod);
    Wallet deductMoneyFromWallet(User user, Double amount , String transactionId , Ride ride ,TransactionMethod transactionMethod);
    void withdrawAllMyMoneyFromWallet();
    Wallet findWalletById(Long walletId);
    Wallet createNewWallet(User user);
    Wallet findByUser(User user);
}
