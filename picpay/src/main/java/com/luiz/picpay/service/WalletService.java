package com.luiz.picpay.service;

import com.luiz.picpay.controller.dto.CreateWalletDto;
import com.luiz.picpay.entity.Wallet;
import com.luiz.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {
       return walletRepository.save(dto.toWallet());
    }
}
