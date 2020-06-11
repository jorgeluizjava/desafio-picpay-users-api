package com.picpay.users.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    @Query(value = "SELECT DISTINCT u.* FROM user u LEFT JOIN account a ON u.id = a.user_id WHERE (u.full_name LIKE :value% OR a.user_name LIKE :value%)", nativeQuery = true)
    List<User> findByNameOrUserName(@Param("value") String value);

    Optional<User> findById(Long id);
}
