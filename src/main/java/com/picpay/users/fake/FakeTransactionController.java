package com.picpay.users.fake;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class FakeTransactionController {

    @PostMapping("/faketransactions")
    public ResponseEntity<FakeTransactionResponse> process(@RequestBody FakeTransactionRequest fakeTransactionRequest) {
        if (!fakeTransactionRequest.accept()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new FakeTransactionResponse("FAILED"));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new FakeTransactionResponse("SUCCESS"));
    }
}
