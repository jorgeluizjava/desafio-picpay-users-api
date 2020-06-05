package com.picpay.users.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.users.validators.UniqueField;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

public class CreateConsumerRequest implements AccountRequest {

    private Long userId;

    @UniqueField(domainAttribute = "userName", klass = Account.class, message = "username already exists")
    @JsonProperty(value = "username")
    private String userName;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public Account toModel(@NotNull User user) {
        Assert.notNull(user, "user is required");
        return new Account(userName, user);
    }
}
