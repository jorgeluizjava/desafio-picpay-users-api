package com.picpay.users.transactions;

import com.picpay.users.users.Account;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Account payee;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Account payer;
    @NotNull
    private BigDecimal value;

    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Frameworks only
     */
    @Deprecated
    public Transaction() {}

    public Transaction(
            @NotNull Account payee,
            @NotNull Account payer,
            @NotNull BigDecimal value) {

        Assert.notNull(payee, "payee is required");
        Assert.notNull(payer, "payer is required");

        this.payee = payee;
        this.payer = payer;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public Account getPayee() {
        return payee;
    }

    public Account getPayer() {
        return payer;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
