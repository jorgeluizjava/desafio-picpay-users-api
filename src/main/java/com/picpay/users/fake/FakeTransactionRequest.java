package com.picpay.users.fake;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class FakeTransactionRequest {

    @JsonProperty("payeeId")
    private Long payeeId;
    @JsonProperty("payerId")
    private Long payerId;
    private BigDecimal value;

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
