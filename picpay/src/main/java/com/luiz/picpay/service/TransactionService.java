package com.luiz.picpay.service;

import com.luiz.picpay.controller.dto.TransactionDto;
import com.luiz.picpay.entity.Transaction;
import com.luiz.picpay.entity.Wallet;
import com.luiz.picpay.exceptions.InsufficientBalanceException;
import com.luiz.picpay.exceptions.TransactionNotAllowedForWalletTypeException;
import com.luiz.picpay.exceptions.TransactionNotAuthorized;
import com.luiz.picpay.exceptions.WalletNotFoundException;
import com.luiz.picpay.repository.TransactionRepository;
import com.luiz.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
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
    @Transactional
    public Transaction transaction(TransactionDto transactionDto){
    var sender = walletRepository.findById(transactionDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transactionDto.payer()));
        var receiver = walletRepository.findById(transactionDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transactionDto.payee()));

        validateTransaction(transactionDto, sender);

        sender.debit(transactionDto.value());
        receiver.credit(transactionDto.value());

        var transaction = new Transaction(sender,receiver,transactionDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transactionResult = transactionRepository.save(transaction);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transactionResult));

        return transactionResult;
    }

    private void validateTransaction(TransactionDto transactionDto, Wallet sender) {
        if(!sender.isTransactionAllowedForWalletType()){
          throw new TransactionNotAllowedForWalletTypeException();
        }
        if (!sender.isBalanceBiggerThan(transactionDto.value())){
            throw new InsufficientBalanceException();
        }
        if(authService.isAuth(transactionDto)){
            throw new TransactionNotAuthorized();
        }
    }
}
