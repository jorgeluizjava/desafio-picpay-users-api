package com.picpay.users.transactions;

import com.picpay.users.shared.FindById;
import com.picpay.users.users.Account;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateTransactionRequest {

    @NotNull(message = "payeeId is required")
    private Long payeeId;
    @NotNull(message = "payerId is required")
    private Long payerId;
    @NotNull
    private BigDecimal value;

    @Deprecated
    public CreateTransactionRequest() {
    }

    public CreateTransactionRequest(
                    @NotNull Long payeeId,
                    @NotNull Long payerId,
                    @NotNull BigDecimal value) {

        Assert.notNull(payeeId, "payeeId is required");
        Assert.notNull(payerId, "payepayerIdeId is required");
        Assert.notNull(value, "value is required");

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

    public Long getPayeeId() {
        return payeeId;
    }

    public Long getPayerId() {
        return payerId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Transaction toModel(EntityManager manager) {
        Account payee = FindById.execute(payeeId, manager, Account.class);
        Account payer = FindById.execute(payerId, manager, Account.class);
        return new Transaction(payee, payer, value);
    }

    public boolean isSameAccount() {
        return payerId.equals(payeeId);
    }
}
