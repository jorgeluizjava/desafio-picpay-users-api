package com.picpay.users.users;

import org.springframework.util.Assert;

public class UserPayload {

    private UserDetailResponse user;
    private AccountDTO accounts;

    /**
     * Frameworks only
     */
    @Deprecated
    public UserPayload() {
    }

    public UserPayload(User user) {
        Assert.notNull(user, "user is required");
        this.user = new UserDetailResponse(user);
        this.accounts = new AccountDTO(user);
    }

    public UserDetailResponse getUser() {
        return user;
    }

    public AccountDTO getAccounts() {
        return accounts;
    }
}
