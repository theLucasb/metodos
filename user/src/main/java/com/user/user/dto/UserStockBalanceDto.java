package com.user.user.dto;

import com.user.user.model.IdUserStocks;
import com.user.user.model.User;
import com.user.user.model.UserStockBalance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStockBalanceDto {
    private Long idUser;

    private Long idStock;

    private String stockSymbol;

    private String stockName;

    private Long volume;

    public UserStockBalance buscarDto(User user) {
        return new UserStockBalance(new IdUserStocks(user, idStock),
                stockSymbol,
                stockName,
                volume);
    }

    public UserStockBalanceDto(UserStockBalance userStockBalance) {
        this.idUser = userStockBalance.getId().getUser().getId();
        this.idStock = userStockBalance.getId().getIdStock();
        this.stockSymbol = userStockBalance.getStockSymbol();
        this.stockName = userStockBalance.getStockName();
        this.volume = userStockBalance.getVolume();
    }

}