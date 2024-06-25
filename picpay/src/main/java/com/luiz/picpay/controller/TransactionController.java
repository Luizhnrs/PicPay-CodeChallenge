package com.luiz.picpay.controller;

import com.luiz.picpay.controller.dto.TransactionDto;
import com.luiz.picpay.entity.Transaction;
import com.luiz.picpay.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transaction(@RequestBody @Valid TransactionDto dto){
        var resp = transactionService.transaction(dto);
        return ResponseEntity.ok(resp);
    }
}
