package com.picpay.users.users;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;
    private String fantasyName;
    private String socialName;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private AccountOwner accountOwner;

    /**
     * Frameworks only
     */
    @Deprecated
    public Seller() {}

    public Seller(@NotNull User user, String userName, String cnpj, String fantasyName, String socialName) {
        this.cnpj = cnpj;
        this.fantasyName = fantasyName;
        this.socialName = socialName;
        this.accountOwner = new AccountOwner(userName, user);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return this.accountOwner.getUser();
    }

    public String getUserName() {
        return this.accountOwner.getUserName();
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
