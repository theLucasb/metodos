package com.user.user.Dto;

import com.user.user.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SaveUserDto {

    private String username;

    private String password;

    private Double dollarBalance;

    private Boolean enabled = true;

    public User transformaParaObjeto() {
        return new User(
                null, username,
                password,
                dollarBalance,
                enabled, null, null);
    }
}
