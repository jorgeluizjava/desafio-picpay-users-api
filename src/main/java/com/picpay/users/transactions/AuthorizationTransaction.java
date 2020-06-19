package com.picpay.users.transactions;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationTransaction {

    public void authorize(CreateTransactionRequest createTransactionRequest) {
        Assert.notNull(createTransactionRequest, "createTransactionRequest is required");
        RestTemplate client = new RestTemplate();
        client.setErrorHandler(new TransactionErrorHandler());

        client.postForEntity("http://localhost:8000/faketransactions", createTransactionRequest, TransactionResponse.class);
    }
}
