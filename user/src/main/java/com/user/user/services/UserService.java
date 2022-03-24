package com.user.user.services;

import java.util.List;
import java.util.Optional;

import com.user.user.Dto.SaveUserDto;
import com.user.user.Dto.UserDto;
import com.user.user.Model.User;
import com.user.user.handleerror.NotFoundException;
import com.user.user.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public SaveUserDto salvar(SaveUserDto saveUserDto) {
        User user = saveUserDto.transformaParaObjeto();
        userRepository.save(user);
        return saveUserDto;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    // public UserDto findByUsername(String username) throws NotFoundException {
    // Optional<User> user = userRepository.findByUsername(username);
    // if (user.isPresent()) {
    // return new UserDto(user.get().getId());
    // } else {
    // throw new NotFoundException("NOT_FOUND");
    // }
    // }

    public Optional<UserDto> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserDto::new);

    }

    // public List<UserDto> listAllDto() {
    // return userRepository.findAll().stream().map((User user) -> new
    // UserDto(user.getId())).toList();
    // }
}
