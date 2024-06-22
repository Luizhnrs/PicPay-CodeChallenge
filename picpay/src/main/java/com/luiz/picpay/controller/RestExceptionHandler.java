package com.luiz.picpay.controller;

import com.luiz.picpay.exceptions.ExistingWalletException;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidAlgorithmParameterException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ExistingWalletException.class)
    public ProblemDetail handleWalletException(ExistingWalletException ex)
    {
        return ex.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        var fieldErrors = ex.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Request Didn`t validate");
        pb.setProperty("Invalid Params", fieldErrors);
        return null;
    }
    private record InvalidParam(String fieldName, String reason){}
}
