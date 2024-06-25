package com.luiz.picpay.service;

import com.luiz.picpay.controller.dto.TransactionDto;
import com.luiz.picpay.entity.Transaction;
import com.luiz.picpay.entity.Wallet;
import com.luiz.picpay.exceptions.WalletNotFoundException;
import com.luiz.picpay.repository.TransactionRepository;
import com.luiz.picpay.repository.WalletRepository;

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AuthService authService;
    private final NotificationService notificationService;
    private final WalletRepository walletRepository;

    public TransactionService(TransactionRepository transactionRepository, AuthService authService, NotificationService notificationService, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.authService = authService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
    }

    public Transaction transaction(TransactionDto transactionDto){
    var sender = walletRepository.findById(transactionDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transactionDto.payer()));
        var receiver = walletRepository.findById(transactionDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transactionDto.payee()));

        validateTransaction(transactionDto, sender);
    }

    private void validateTransaction(TransactionDto transactionDto, Wallet sender) {
        if(!sender.isTransactionAllowedForWalletType()){
          throw
        }
    }
}
