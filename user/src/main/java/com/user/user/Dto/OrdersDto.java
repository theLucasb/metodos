package com.user.user.Dto;

import com.user.user.Model.Orders;
import com.user.user.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdersDto {

    private Long idUser;

    private Long idStock;

    private String stockSymbol;

    private String stockName;

    private Long volume;

    private Integer type;

    private Integer status;

    private Double price;

    private Long volumeRemaining;

    public Orders buscarDto(User users) {
        Orders orders = new Orders();
        orders.setUsers(users);
        orders.setIdStock(idStock);
        orders.setStockSymbol(stockSymbol);
        orders.setStockName(stockName);
        orders.setVolume(volume);
        orders.setType(type);
        orders.setStatus(status);
        orders.setPrice(price);
        orders.setVolumeRemaining(volumeRemaining);

        return orders;
    }
}
