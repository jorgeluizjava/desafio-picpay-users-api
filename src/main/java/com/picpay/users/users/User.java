package com.picpay.users.users;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String email;
    private String fullName;
    private String password;
    private String phoneNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Account> accounts = new HashSet<>();

    /**
     * Frameworks only
     */
    @Deprecated
    public User() {}

    public User(String cpf, String email, String fullName, String password, String phoneNumber) {
        this.cpf = cpf;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void add(@NotNull Account account) {
        Assert.notNull(account, "account is required");
        Optional<Account> alreadyExists = accountTypeAlreadyExists(account);
        if (alreadyExists.isPresent()) {
            throw new IllegalArgumentException("Account type already exists!");
        }
        accounts.add(account);
    }

    public Optional<Account> getAccout(AccountType accountType) {
        return accounts
                .stream()
                .filter(currentAccount -> currentAccount.isSameType(accountType))
                .findFirst();
    }

    private Optional<Account> accountTypeAlreadyExists(Account account) {
        return accounts
                .stream()
                .filter(currentAccount -> currentAccount.isSameType(account.getAccountType()))
                .findAny();
    }
}
