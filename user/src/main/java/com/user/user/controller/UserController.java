package com.user.user.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import com.user.user.services.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.user.user.Dto.SaveUserDto;
import com.user.user.Dto.UserDto;
import com.user.user.Model.User;

@CrossOrigin("http://localhost:8081/")
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> listaUsers() {
        return userService.listAll();
    }

    // @GetMapping("/users-id")
    // public List<UserDto> listUsersId() throws InterruptedException {
    // Thread.sleep(3000);

    // return userService.listAllDto();
    // }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok().body(userService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<Optional<UserDto>> findByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }

    @PostMapping("/new_user")
    public ResponseEntity<SaveUserDto> salvar(@RequestBody SaveUserDto dto) {
        return ResponseEntity.ok().body(userService.salvar(dto));
    }

}
