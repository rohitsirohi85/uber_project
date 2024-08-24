package com.uberApplication.uber.DTO;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {
    private Long id;

    private UserDto user;

    private double balance;


    private List<WalletTransactionDto> transactions;
}
