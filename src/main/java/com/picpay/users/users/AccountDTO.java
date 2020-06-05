package com.picpay.users.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.Assert;

import java.util.Optional;

public class AccountDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Seller seller;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Consumer consumer;

    public AccountDTO(User user) {
        Assert.notNull(user, "user is required");
        extractConsumerAccout(user);
        extractSellerAccout(user);
    }

    public Seller getSeller() {
        return seller;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    private void extractConsumerAccout(User user) {
        Optional<Account> optionalConsumerAccount = user.getAccout(AccountType.CONSUMER);
        if (optionalConsumerAccount.isPresent()) {
            this.consumer = new Consumer(optionalConsumerAccount.get());
        }
    }

    private void extractSellerAccout(User user) {
        Optional<Account> optionalConsumerAccount = user.getAccout(AccountType.SELLER);
        if (optionalConsumerAccount.isPresent()) {
            this.seller = new Seller(optionalConsumerAccount.get());
        }
    }
}
