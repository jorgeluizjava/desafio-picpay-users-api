package com.picpay.users.users;

import org.springframework.util.Assert;

import java.util.Optional;

public class UserPayload {

    private UserDetailDTO user;
    private UserAccountsDTO accounts;

    /**
     * Frameworks only
     */
    @Deprecated
    public UserPayload() {
    }

    public UserPayload(User user, Optional<Seller> optionalSeller, Optional<Consumer> optionalConsumer) {
        Assert.notNull(user, "user is required");
        this.user = new UserDetailDTO(user);
        this.accounts = new UserAccountsDTO(optionalSeller, optionalConsumer);
    }

    public UserDetailDTO getUser() {
        return user;
    }

    public UserAccountsDTO getAccounts() {
        return accounts;
    }
}
