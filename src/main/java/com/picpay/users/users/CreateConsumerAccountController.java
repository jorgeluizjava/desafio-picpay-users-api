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
public class CreateConsumerAccountController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping(value = "/users/consumers")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumerDTO create(@RequestBody @Valid CreateConsumerRequest createConsumerRequest) {
        Consumer consumer = createConsumerRequest.toModel(manager);
        manager.persist(consumer);
        return new ConsumerDTO(consumer);
    }
}
