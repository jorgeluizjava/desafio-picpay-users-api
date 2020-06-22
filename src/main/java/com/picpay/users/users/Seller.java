package com.picpay.users.users;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    private Long id;

    private String cnpj;
    private String fantasyName;
    private String socialName;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Account account;

    /**
     * Frameworks only
     */
    @Deprecated
    public Seller() {}

    public Seller(@NotNull User user, String userName, String cnpj, String fantasyName, String socialName) {
        this.cnpj = cnpj;
        this.fantasyName = fantasyName;
        this.socialName = socialName;
        this.account = new Account(userName, user);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return this.account.getUser();
    }

    public String getUserName() {
        return this.account.getUserName();
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

}
