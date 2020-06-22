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
public class CreateSellerAccountController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/users/sellers")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public SellerDTO create(@RequestBody @Valid CreateSellerRequest createSellerRequest) {
        Seller seller = createSellerRequest.toModel(manager);
        manager.persist(seller);
        return new SellerDTO(seller);
    }
}
