package com.luiz.picpay.controller.dto;

import com.luiz.picpay.entity.Wallet;
import com.luiz.picpay.entity.WalletType;

import java.math.BigDecimal;

public record CreateWalletDto(String fullName, String cpfCnpj, String email, String password, WalletType.Enum walletType) {
    public Wallet toWallet()
    {
        return new Wallet(
                fullName,
                cpfCnpj,
                email,
                password,
                walletType.get()
        );
    }
}
