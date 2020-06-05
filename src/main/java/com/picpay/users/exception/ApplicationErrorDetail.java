package com.picpay.users.exception;

public class ApplicationErrorDetail {

    private String code;
    private String message;

    public ApplicationErrorDetail(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
