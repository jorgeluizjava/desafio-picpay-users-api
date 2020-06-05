package com.picpay.users.transactions;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDetailResponse {

    private Long id;
    private Long payeeId;
    private Long payerId;
    private LocalDateTime transactionDate;
    private BigDecimal value;

    public TransactionDetailResponse(Transaction transaction) {
        Assert.notNull(transaction, "transaction is required");
        this.id = transaction.getId();
        this.payeeId = transaction.getPayee().getId();
        this.payerId = transaction.getPayer().getId();
        this.transactionDate = transaction.getCreatedAt();
        this.value = transaction.getValue();
    }

    public Long getId() {
        return id;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public Long getPayerId() {
        return payerId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public BigDecimal getValue() {
        return value;
    }
}
