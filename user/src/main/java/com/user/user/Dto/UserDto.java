package com.user.user.Dto;

import com.user.user.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String username;
    private double dollarBalance;

    public UserDto(User user) {
        id = user.getId();
        username = user.getUsername();
        dollarBalance = user.getDollarBalance();
    }

}
