package com.picpay.users.users;

import com.picpay.users.shared.FindById;
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
    public Seller create(@RequestBody @Valid CreateSellerRequest createSellerRequest) {
        User user = FindById.execute(createSellerRequest.getUserId(), manager, User.class);
        Account sellerAccount = createSellerRequest.toModel(user);
        user.add(sellerAccount);

        manager.merge(user);

        return new Seller(user.getAccout(AccountType.SELLER).get());
    }
}
