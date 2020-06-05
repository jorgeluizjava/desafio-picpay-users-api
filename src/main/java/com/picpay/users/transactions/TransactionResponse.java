package com.picpay.users.transactions;

public class TransactionResponse {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAuthorized() {
        return status.equals("SUCCESS");
    }
}
