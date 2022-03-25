package com.user.user.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.user.user.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "UPDATE users set dollar_balance = :dollar_balance where id = :id", nativeQuery = true)
    Integer findbySetDollar(@Param("id") Long id,
            @Param("dollar_balance") Double dollarBalance);

    @Query(value = "SELECT * FROM users where username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

}
