package com.luiz.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransactionNotAuthorized extends BankException {
    @Override
    public ProblemDetail toProblemDetail() {
       var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
       pb.setTitle("Transaction Not Authorized");
       pb.setDetail("Authorization service is not authorized this transfer");
       return pb;
    }
}
