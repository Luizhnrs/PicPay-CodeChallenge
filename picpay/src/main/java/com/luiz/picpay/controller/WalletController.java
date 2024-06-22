package com.luiz.picpay.controller;

import com.luiz.picpay.controller.dto.CreateWalletDto;
import com.luiz.picpay.entity.Wallet;
import com.luiz.picpay.repository.WalletTypeRepository;
import com.luiz.picpay.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }
    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto){
       var wallet = walletService.createWallet(dto);
       return ResponseEntity.ok(wallet);
    }

}
