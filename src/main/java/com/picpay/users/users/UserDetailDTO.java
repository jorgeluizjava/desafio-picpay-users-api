package com.picpay.users.users;

import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

public class UserDetailDTO {

    private Long id;
    private String cpf;
    private String email;
    private String fullName;
    private String password;
    private String phoneNumber;

    /**
     * Frameworks only
     */
    @Deprecated
    public UserDetailDTO() {}

    public UserDetailDTO(@NotNull User user) {
        Assert.notNull(user, "user is required");
        this.id = user.getId();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.password = user.getPassword();
        this.phoneNumber = user.getPhoneNumber();
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
}
