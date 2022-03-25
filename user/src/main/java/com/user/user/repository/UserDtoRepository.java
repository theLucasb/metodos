package com.user.user.repository;

import com.user.user.dto.UserDto;
import com.user.user.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDtoRepository extends JpaRepository<User, Long> {
    UserDto findByUsername(String username);

}
