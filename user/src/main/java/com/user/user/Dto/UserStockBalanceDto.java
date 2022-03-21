package com.user.user.Dto;

import com.user.user.Model.IdUserStocks;
import com.user.user.Model.User;
import com.user.user.Model.UserStockBalance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserStockBalanceDto {

    private Long idUser;
    private Long idStock;
    private String stockSymbol;
    private String stockName;
    private Integer volume;

    public UserStockBalance userStockObj(User user) {
        return new UserStockBalance(
                new IdUserStocks(user, getIdStock()),
                stockSymbol,
                stockName,
                volume);

    }

    // public UserStockBalanceDto(UserStockBalanceDto userStockBalance) {
    // idUser = userStockBalance.getIdUser();
    // stockSymbol = userStockBalance.getStockSymbol();
    // stockName = userStockBalance.getStockName();
    // volume = userStockBalance.getVolume();
    // }

}