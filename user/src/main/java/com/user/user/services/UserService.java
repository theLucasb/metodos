package com.user.user.services;

import java.util.List;
import java.util.Optional;

import com.user.user.dto.SaveUserDto;
import com.user.user.dto.UserDto;
import com.user.user.model.User;
import com.user.user.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public SaveUserDto salvar(SaveUserDto saveUserDto) {
        User user = saveUserDto.buscarDto();
        userRepository.save(user);
        return saveUserDto;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Optional<UserDto> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserDto::new);

    }

}
