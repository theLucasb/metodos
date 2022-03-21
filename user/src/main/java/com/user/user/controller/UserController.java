package com.user.user.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import com.user.user.repository.UserRepository;
import com.user.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.reactive.function.client.WebClient;

import com.user.user.Dto.UserDto;
import com.user.user.Model.User;

@RestController
@RequestMapping("/user")
public class UserController {

    // @Autowired
    // private WebClient.Builder webClient;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> listaUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Optional<UserDto>> findByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }

    @PostMapping
    public User add(@RequestBody User user, String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", accessToken);
        return userRepository.save(user);
    }

    @DeleteMapping
    public void delete(@RequestBody User user) {
        userRepository.delete(user);
    }

}
