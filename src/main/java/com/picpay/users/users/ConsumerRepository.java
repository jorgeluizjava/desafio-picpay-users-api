package com.picpay.users.users;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ConsumerRepository extends Repository<Consumer, Long> {

    Optional<Consumer> findByAccountUser(User user);

    Optional<Consumer> findById(Long id);
}
