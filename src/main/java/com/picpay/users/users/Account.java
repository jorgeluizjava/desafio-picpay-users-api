package com.picpay.users.users;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Account {

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
        Assert.hasText(userName, "userName is required");
        Assert.notNull(user, "user is required");
        this.userName = userName;
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public User getUser() {
        return user;
    }
}
