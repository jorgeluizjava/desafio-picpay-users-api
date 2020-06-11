package com.picpay.users.users;

import com.picpay.users.validators.UniqueField;

import javax.validation.constraints.NotBlank;

public class CreateUserRequest {

    @NotBlank
    @UniqueField(domainAttribute = "cpf", klass = User.class, message = "CPF already exists")
    private String cpf;
    @NotBlank
    @UniqueField(domainAttribute = "email", klass = User.class, message = "E-mail already exists")
    private String email;
    private String fullName;
    private String password;
    private String phoneNumber;

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User toModel() {
        return new User(cpf, email, fullName, password, phoneNumber);
    }
}
