package com.picpay.users.users;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class CreateUserController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/users")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public UserDetailResponse create(@RequestBody @Valid CreateUserRequest createUserRequest) {
        User user = createUserRequest.toModel();
        manager.persist(user);
        return new UserDetailResponse(user);
    }
}
