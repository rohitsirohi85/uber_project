package com.uberApplication.uber.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uberApplication.uber.entities.Ride;
import com.uberApplication.uber.entities.User;
import com.uberApplication.uber.entities.Wallet;
import com.uberApplication.uber.entities.WalletTransaction;
import com.uberApplication.uber.entities.enums.TransactionMethod;
import com.uberApplication.uber.entities.enums.TransactionType;
import com.uberApplication.uber.exception.ResourceNotFoundException;
import com.uberApplication.uber.repository.WalletRepo;
import com.uberApplication.uber.services.WalletService;
import com.uberApplication.uber.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
   
    private final WalletTransactionService walletTransactionService;
    private final WalletRepo walletRepo;
    
    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount , String transactionId , Ride ride ,TransactionMethod transactionMethod) {
      Wallet wallet =findByUser(user);
      wallet.setBalance(wallet.getBalance()+amount);

         WalletTransaction walletTransaction = WalletTransaction.builder()
                          .transactionId(transactionId)
                          .ride(ride)
                          .transactionType(TransactionType.CREDIT)
                          .transactionMethod(transactionMethod)
                          .amount(amount)
                          .build();

                         walletTransactionService.createNewWalletTransaction(walletTransaction);

                    

      return walletRepo.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdrawAllMyMoneyFromWallet'");
    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepo.findById(walletId).orElseThrow(()->new ResourceNotFoundException("wallet not found with id:"+walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepo.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
   return walletRepo.findByUser(user).orElseThrow((()->new ResourceNotFoundException("don't find any user with id:"+user.getId())));
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount , String transactionId , Ride ride ,TransactionMethod transactionMethod) {
        
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
        .transactionId(transactionId)
        .ride(ride)
        .transactionType(TransactionType.DEBIT)
        .transactionMethod(transactionMethod)
        .amount(amount)
        .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepo.save(wallet);
    }
    
}
