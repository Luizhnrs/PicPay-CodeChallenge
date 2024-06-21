package com.luiz.picpay.controller;

import com.luiz.picpay.exceptions.ExistingWalletException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ExistingWalletException.class)
    public ProblemDetail handleWalletException(ExistingWalletException ex)
    {
        return ex.toProblemDetail();
    }

}
