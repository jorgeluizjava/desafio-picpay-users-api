package com.picpay.users.transactions;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CheckIfNotSameAccountValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateTransactionRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateTransactionRequest createTransactionRequest = (CreateTransactionRequest) target;
        if (createTransactionRequest.isSameAccount()) {
            errors.rejectValue("payeeId", null, "payeeId and payerId cannot be the same");
        }
    }
}
