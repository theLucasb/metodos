package com.user.user.model.entity;

import com.user.user.Dto.UserDto;
import com.user.user.repository.UserDtoRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired
    UserDtoRepository userDtoRepository;

    @Test
    @DisplayName("Teste User")
    public void testeUser() {
        UserDto atual = userDtoRepository.findByUsername("deyvinho@gmail.com");
        UserDto esperado = new UserDto(1L, "deyvinho@gmail.com", 100);
        Assertions.assertEquals(atual, esperado, "teste test");
    }
}
