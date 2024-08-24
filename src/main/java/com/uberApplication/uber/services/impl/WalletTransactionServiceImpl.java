package com.uberApplication.uber.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.uberApplication.uber.entities.WalletTransaction;
import com.uberApplication.uber.repository.WalletTransactionRepo;
import com.uberApplication.uber.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {
    
    private final WalletTransactionRepo walletTransactionRepo;
    private final ModelMapper modelMapper;
    
    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
      
      walletTransactionRepo.save(walletTransaction);
    }
    
}
