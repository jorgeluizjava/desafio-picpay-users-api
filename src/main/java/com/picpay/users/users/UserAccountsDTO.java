package com.picpay.users.users;

import java.util.Optional;

public class UserAccountsDTO {

    private SellerDTO seller;

    private ConsumerDTO consumer;

    public UserAccountsDTO(Optional<Seller> optionalSeller, Optional<Consumer> optionalConsumer) {
        this.seller = (optionalSeller.isPresent() ? new SellerDTO(optionalSeller.get()) : null);
        this.consumer = (optionalConsumer.isPresent() ? new ConsumerDTO(optionalConsumer.get()) : null);
    }

    public SellerDTO getSeller() {
        return seller;
    }

    public ConsumerDTO getConsumer() {
        return consumer;
    }
}
