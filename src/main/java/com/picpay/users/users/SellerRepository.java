package com.picpay.users.users;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SellerRepository extends Repository<Seller, Long> {

    Optional<Seller> findByAccountUser(User user);

    Optional<Seller> findById(Long id);
}
