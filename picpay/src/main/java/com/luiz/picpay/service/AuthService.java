package com.luiz.picpay.service;

import com.luiz.picpay.client.AuthClient;
import com.luiz.picpay.controller.dto.TransactionDto;
import com.luiz.picpay.exceptions.BankException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthClient authClient;

    public AuthService(AuthClient authClient) {
        this.authClient = authClient;
    }
    public boolean isAuth(TransactionDto transaction)
    {
        var resp = authClient.isAuth();
        if (resp.getStatusCode().isError()){
            throw new BankException();
        }
        return resp.getBody().authorized();
    }

}
