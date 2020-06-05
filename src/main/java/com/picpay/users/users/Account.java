package com.picpay.users.users;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String userName;

    private String cnpj;
    private String fantasyName;
    private String socialName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NotNull
    @ManyToOne
    private User user;

    /**
     * Frameworks only
     */
    @Deprecated
    public Account() {
    }

    /**
     * Constructor for AccountType.CONSUMER
     * @param userName
     * @param user
     */
    public Account(@NotBlank String userName, @NotNull User user) {
        Assert.hasText(userName, "userName is required");
        Assert.notNull(user, "user is required");
        this.userName = userName;
        this.user = user;
        this.accountType = AccountType.CONSUMER;
    }

    /**
     * Constructor for AccountType.SELLER
     * @param userName
     * @param cnpj
     * @param fantasyName
     * @param socialName
     * @param user
     */
    public Account(
            @NotBlank String userName,
            String cnpj,
            String fantasyName,
            String socialName,
            @NotNull User user) {

        Assert.hasText(userName, "userName is required");
        Assert.notNull(user, "user is required");

        this.userName = userName;
        this.user = user;
        this.cnpj = cnpj;
        this.fantasyName = fantasyName;
        this.socialName = socialName;
        this.accountType = AccountType.SELLER;
    }

    public boolean isSameType(AccountType accountType) {
        return this.accountType.equals(accountType);
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public String getSocialName() {
        return socialName;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return userName.equals(account.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

}
