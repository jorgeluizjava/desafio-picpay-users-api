package com.picpay.users.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.users.shared.FindById;
import com.picpay.users.validators.UniqueField;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

public class CreateConsumerRequest implements AccountRequest {

    @UniqueField(domainAttribute = "accountOwner.user.id", klass = Consumer.class, message = "userId already exists")
    private Long userId;

    @UniqueField(domainAttribute = "accountOwner.userName", klass = Consumer.class, message = "username already exists")
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

    public Consumer toModel(EntityManager manager) {
        Assert.notNull(manager, "manager is required");
        User user = FindById.execute(userId, manager, User.class);
        return new Consumer(user, userName);
    }
}
