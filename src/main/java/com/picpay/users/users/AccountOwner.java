package com.picpay.users.users;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String userName;

    @NotNull
    @ManyToOne
    private User user;

    @Deprecated
    public Account() {
    }

    public Account(@NotBlank String userName, @NotNull User user) {
        this.userName = userName;
        this.user = user;
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
