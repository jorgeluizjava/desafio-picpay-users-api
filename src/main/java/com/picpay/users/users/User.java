package com.picpay.users.users;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Email
    private String email;

    private String fullName;
    private String password;
    private String phoneNumber;

    /**
     * Frameworks only
     */
    @Deprecated
    public User() {}

    public User(@NotBlank @CPF String cpf, @NotBlank @CPF String email, String fullName, String password, String phoneNumber) {
        Assert.hasText(cpf, "CPF is required");
        Assert.hasText(email, "email is required");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return cpf.equals(user.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
