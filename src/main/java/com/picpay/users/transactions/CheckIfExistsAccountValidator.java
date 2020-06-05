package com.picpay.users.transactions;

import com.picpay.users.users.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

public class CheckIfExistsAccountValidator implements Validator {

    private EntityManager manager;

    public CheckIfExistsAccountValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateTransactionRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateTransactionRequest createTransactionRequest = (CreateTransactionRequest) target;
        Account payee = manager.find(Account.class, createTransactionRequest.getPayeeId());
        if (payee == null) {
            errors.rejectValue("payeeId", null, "payeeId does not exist");
        }
        Account payer = manager.find(Account.class, createTransactionRequest.getPayerId());
        if (payer == null) {
            errors.rejectValue("payerId", null, "payerId does not exist");
        }
    }
}
