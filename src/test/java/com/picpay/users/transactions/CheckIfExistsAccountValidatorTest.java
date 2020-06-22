package com.picpay.users.transactions;

import com.picpay.users.users.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CheckIfExistsAccountValidatorTest {

    private EntityManager entityManager;

    private CheckIfExistsAccountValidator checkIfExistsAccountValidator;

    @Before
    public void init() {
        entityManager = Mockito.mock(EntityManager.class);
        checkIfExistsAccountValidator = new CheckIfExistsAccountValidator(entityManager);
    }

    @Test
    public void shouldReturnEmptyErrorsListWhenPayeeAndPayerAccountExists() {

        Long payeeId = 1L;
        Long payerId = 2L;

        Mockito.when(entityManager.find(Account.class, payeeId)).thenReturn(new Account());
        Mockito.when(entityManager.find(Account.class, payerId)).thenReturn(new Account());

        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest(payeeId, payerId, new BigDecimal("30.0"));

        Errors errors = new BeanPropertyBindingResult(createTransactionRequest, "createTransactionRequest");
        checkIfExistsAccountValidator.validate(createTransactionRequest, errors);

        assertThat(errors.getAllErrors(), is(empty()));
    }

    @Test
    public void shouldReturnNonEmptyErrorsListWhenPayeeAccountDoesNotExists() {

        Long payeeId = 1L;
        Long payerId = 2L;

        Mockito.when(entityManager.find(Account.class, payeeId)).thenReturn(null);
        Mockito.when(entityManager.find(Account.class, payerId)).thenReturn(new Account());

        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest(payeeId, payerId, new BigDecimal("30.0"));

        Errors errors = new BeanPropertyBindingResult(createTransactionRequest, "createTransactionRequest");
        checkIfExistsAccountValidator.validate(createTransactionRequest, errors);

        assertThat(errors.getAllErrors(), is(not(empty())));

        FieldError fieldError = errors.getFieldError();
        assertThat(fieldError.getField(), is(equalTo("payeeId")));
        assertThat(fieldError.getDefaultMessage(), is(equalTo("payeeId does not exist")));
    }

    @Test
    public void shouldReturnNonEmptyErrorsListWhenPayerAccountDoesNotExists() {

        Long payeeId = 1L;
        Long payerId = 2L;

        Mockito.when(entityManager.find(Account.class, payeeId)).thenReturn(new Account());
        Mockito.when(entityManager.find(Account.class, payerId)).thenReturn(null);

        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest(payeeId, payerId, new BigDecimal("30.0"));

        Errors errors = new BeanPropertyBindingResult(createTransactionRequest, "createTransactionRequest");
        checkIfExistsAccountValidator.validate(createTransactionRequest, errors);

        assertThat(errors.getAllErrors(), is(not(empty())));

        FieldError fieldError = errors.getFieldError();

        assertThat(fieldError.getField(), is(equalTo("payerId")));
        assertThat(fieldError.getDefaultMessage(), is(equalTo("payerId does not exist")));
    }
}