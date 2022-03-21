package com.user.user.services;

import java.util.Optional;

import com.user.user.Dto.UserDto;
import com.user.user.Model.User;
import com.user.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    public Optional<UserDto> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<UserDto> userDTO = user.map(dto -> new UserDto(dto));
        return userDTO;

    }

}
