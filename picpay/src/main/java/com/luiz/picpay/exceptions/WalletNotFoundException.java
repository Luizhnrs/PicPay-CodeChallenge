package com.luiz.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends BankException {
private Long walletId;
public WalletNotFoundException(Long walletId){
    this.walletId = walletId;
}

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Wallet not found");
        pb.setDetail("There is no wallet with id" + walletId + ".");
    }
}
