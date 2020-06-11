package com.picpay.users.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

public class SellerDTO {

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
    public SellerDTO() {
    }

    public SellerDTO(Seller seller) {
        Assert.notNull(seller, "seller is required");
        this.id = seller.getId();
        this.userId = seller.getUser().getId();
        this.userName = seller.getUserName();
        this.cnpj = seller.getCnpj();
        this.fantasyName = seller.getFantasyName();
        this.socialName = seller.getSocialName();
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
