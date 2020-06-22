package com.picpay.users.fake;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class FakeTransactionRequest {

    @JsonProperty("payeeId")
    private Long payeeId;
    @JsonProperty("payerId")
    private Long payerId;
    private BigDecimal value;

    public FakeTransactionRequest() {
    }

    public FakeTransactionRequest(Long payeeId, Long payerId, BigDecimal value) {
        this.payeeId = payeeId;
        this.payerId = payerId;
        this.value = value;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean accept() {
        return value.compareTo(new BigDecimal("100")) < 0;
    }
}
