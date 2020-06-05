package com.picpay.users.exception;

public class TransactionNotAuthorizedException extends RuntimeException {

    public TransactionNotAuthorizedException(String message) {
        super(message);
    }
}
