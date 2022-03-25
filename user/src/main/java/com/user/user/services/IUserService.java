package com.user.user.services;

import java.util.Optional;

import com.user.user.dto.UserDto;

import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    Optional<UserDto> findByUsername(String username);

}
