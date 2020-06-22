package com.picpay.users.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.picpay.users.shared.FindById;
import com.picpay.users.validators.UniqueField;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

public class CreateSellerRequest {

    private String cnpj;
    private String fantasyName;
    private String socialName;

    @UniqueField(domainAttribute = "account.user.id", klass = Seller.class, message = "userId already exists")
    private Long userId;

    @UniqueField(domainAttribute = "account.userName", klass = Seller.class, message = "username already exists")
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

    public Seller toModel(EntityManager manager) {
        Assert.notNull(manager, "manager is required");
        User user = FindById.execute(userId, manager, User.class);
        return new Seller(user, userName, cnpj, fantasyName, socialName);
    }
}
