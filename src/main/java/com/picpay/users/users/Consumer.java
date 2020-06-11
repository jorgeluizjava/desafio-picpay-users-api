package com.picpay.users.users;



import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "consumer")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private AccountOwner accountOwner;

    /**
     * Frameworks only
     */
    @Deprecated
    public Consumer() {}

    public Consumer(@NotNull User user, @NotBlank String userName) {
        Assert.notNull(user, "user is required");
        Assert.hasText(userName, "userName is required");
        this.accountOwner = new AccountOwner(userName, user);
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return this.accountOwner.getUserName();
    }

    public User getUser() {
        return this.accountOwner.getUser();
    }
}
