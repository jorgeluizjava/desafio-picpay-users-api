package com.picpay.users.users;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

public class ConsumerDTO {

    private Long id;
    private Long userId;
    @JsonProperty("username")
    private String userName;

    public ConsumerDTO(Consumer consumer) {
        Assert.notNull(consumer, "consumer is required");
        this.id = consumer.getId();
        this.userId = consumer.getUser().getId();
        this.userName = consumer.getUserName();
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
