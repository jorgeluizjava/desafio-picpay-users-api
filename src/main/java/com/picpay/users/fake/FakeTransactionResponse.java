package com.picpay.users.fake;

public class FakeTransactionResponse {

    private String status;

    public FakeTransactionResponse(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
