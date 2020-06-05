package com.picpay.users.transactions;

import com.picpay.users.exception.TransactionNotAuthorizedException;
import com.picpay.users.shared.FindById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class TransactionsController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private RestTemplate client;

    @InitBinder(value = "transactionRequest")
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(new CheckIfExistsAccountValidator(manager));
    }

    @PostMapping(value = "/transactions")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDetailResponse create(@RequestBody CreateTransactionRequest createTransactionRequest) {

        ResponseEntity<TransactionResponse> postForEntity = client.postForEntity("http://localhost:8000/faketransactions", createTransactionRequest, TransactionResponse.class);
        TransactionResponse transactionResponse = postForEntity.getBody();

        if (!transactionResponse.isAuthorized()) {
            throw new TransactionNotAuthorizedException("Transaction not authorized");
        }

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
