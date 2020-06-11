package com.picpay.users.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

public class Seller {

    private Long id;
    @JsonProperty("username")
    private String userName;
    private String cnpj;
    private String fantasyName;
    private String socialName;
    @JsonProperty("user_id")
    private Long userId;

    /**
     * Frameworks only
     */
    @Deprecated
    public Seller() {
    }

    public Seller(Account account) {
        Assert.notNull(account, "account is required");
        this.id = account.getId();
        this.userId = account.getUser().getId();
        this.userName = account.getUserName();
        this.cnpj = account.getCnpj();
        this.fantasyName = account.getFantasyName();
        this.socialName = account.getSocialName();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public String getSocialName() {
        return socialName;
    }

    public Long getUserId() {
        return userId;
    }
}
