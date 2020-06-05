package com.picpay.users.users;

import com.picpay.users.shared.FindById;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public Consumer create(@RequestBody @Valid CreateConsumerRequest createConsumerRequest) {
        User user = FindById.execute(createConsumerRequest.getUserId(), manager, User.class);
        Account consumerAccount = createConsumerRequest.toModel(user);
        user.add(consumerAccount);

        manager.merge(user);

        return new Consumer(user.getAccout(AccountType.CONSUMER).get());
    }
}
