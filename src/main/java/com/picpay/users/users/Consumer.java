package com.picpay.users.users;



import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "consumer")
public class Consumer {

    @Id
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Account account;

    /**
     * Frameworks only
     */
    @Deprecated
    public Consumer() {}

    public Consumer(@NotNull User user, @NotBlank String userName) {
        Assert.notNull(user, "user is required");
        Assert.hasText(userName, "userName is required");
        this.account = new Account(userName, user);
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return this.account.getUserName();
    }

    public User getUser() {
        return this.account.getUser();
    }
}
