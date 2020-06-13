package com.picpay.users.transactions;

import com.picpay.users.shared.FindById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class TransactionsController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private AuthorizationTransaction authorizationTransaction;

    @InitBinder(value = "createTransactionRequest")
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(new CheckIfExistsAccountValidator(manager));
        dataBinder.addValidators(new CheckIfNotSameAccountValidator());
    }

    @PostMapping(value = "/transactions")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDetailResponse create(@RequestBody @Valid CreateTransactionRequest createTransactionRequest) {

        authorizationTransaction.authorize(createTransactionRequest);

        Transaction transaction = createTransactionRequest.toModel(manager);
        manager.persist(transaction);

        return new TransactionDetailResponse(transaction);
    }

    @GetMapping("/transactions/{transaction_id}")
    public TransactionDetailResponse findById(@PathVariable("transaction_id") Long id) {
        Transaction transaction = FindById.execute(id, manager, Transaction.class);
        return new TransactionDetailResponse(transaction);
    }

}
