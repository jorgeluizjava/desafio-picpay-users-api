package com.picpay.users.fake;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FakeTransactionRequestTest {

    @Test
    public void shouldReturnTrueWhenValueIsLessThan100() {

        Long payeeId = 1L;
        Long payerId = 2L;
        BigDecimal value = new BigDecimal("50.0");

        FakeTransactionRequest fakeTransactionRequest = new FakeTransactionRequest(payeeId, payerId, value);
        boolean isAccept = fakeTransactionRequest.accept();
        assertTrue(isAccept);
    }

    @Test
    public void shouldReturnFalseWhenValueIsEqualTo100() {

        Long payeeId = 1L;
        Long payerId = 2L;
        BigDecimal value = new BigDecimal("100.0");

        FakeTransactionRequest fakeTransactionRequest = new FakeTransactionRequest(payeeId, payerId, value);
        boolean isAccept = fakeTransactionRequest.accept();
        assertFalse(isAccept);
    }

    @Test
    public void shouldReturnFalseWhenValueIsGratherThan100() {

        Long payeeId = 1L;
        Long payerId = 2L;
        BigDecimal value = new BigDecimal("200.0");

        FakeTransactionRequest fakeTransactionRequest = new FakeTransactionRequest(payeeId, payerId, value);
        boolean isAccept = fakeTransactionRequest.accept();
        assertFalse(isAccept);
    }

}