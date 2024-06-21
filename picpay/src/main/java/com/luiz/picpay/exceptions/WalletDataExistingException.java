package com.luiz.picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataExistingException extends ExistingWalletException{
   private String detail;
   public WalletDataExistingException(String detail){
    this.detail = detail;
   }
    @Override
    public ProblemDetail toProblemDetail() {
       var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
       pb.setTitle("Wallet Data Already Exists");
       pb.setDetail(detail);
       return pb;
    }
}
