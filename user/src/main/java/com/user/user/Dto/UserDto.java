package com.user.user.Dto;

import com.user.user.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private Double dollarBalance;

    public UserDto(User user) {
        id = user.getId();
        username = user.getUsername();
        dollarBalance = user.getDollarBalance();
    }

    public User transformaParaObjeto() {
        return new User(
                id, null, null, null, null, null, null);
    }

}
