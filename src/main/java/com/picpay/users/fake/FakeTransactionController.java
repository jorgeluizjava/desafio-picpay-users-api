package com.picpay.users.fake;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class FakeTransactionController {

    @PostMapping("/faketransactions")
    public FakeTransactionResponse process(@RequestBody FakeTransactionRequest fakeTransactionRequest) {
        String status = (fakeTransactionRequest.getValue().compareTo(new BigDecimal("100")) < 0 ? "SUCCESS": "FAILD");
        return new FakeTransactionResponse(status);
    }
}
