package com.picpay.users.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.users.validators.UniqueField;

public class CreateSellerRequest implements AccountRequest {

    private String cnpj;
    private String fantasyName;
    private String socialName;
    private Long userId;

    @UniqueField(domainAttribute = "userName", klass = Account.class, message = "username already exists")
    @JsonProperty(value = "username")
    private String userName;

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }

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

    public Account toModel(User user) {
        return new Account(userName, cnpj, fantasyName, socialName, user);
    }

}
