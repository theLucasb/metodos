package com.user.user.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.user.user.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
