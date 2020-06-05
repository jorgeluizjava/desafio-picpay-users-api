package com.picpay.users.users;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

public class Consumer {

    private Long id;
    private Long userId;
    @JsonProperty("username")
    private String userName;

    public Consumer(Account account) {
        Assert.notNull(account, "account is required");
        this.id = account.getId();
        this.userId = account.getUser().getId();
        this.userName = account.getUserName();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
