package com.user.user.Dto;

import com.user.user.Model.Orders;
import com.user.user.Model.User;

import lombok.Data;

@Data
public class OrdersDto {
    private Long id;
    private User idUser;
    private Long idStock;
    private String stockSymbol;
    private String stockName;
    private Long volume;
    private Long volumeRemaining;
    private double price;
    private Integer type;
    private Integer status;

    public OrdersDto(Long id, User idUser, Long idStock, String stockSymbol,
            String stockName, Long volume, double price, Integer type, Integer status) {
        this.id = id;
        this.idUser = idUser;
        this.idStock = idStock;
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
        this.volume = volume;
        this.price = price;
        this.type = type;
        this.status = status;
    }

    public OrdersDto(Orders orders) {
        id = orders.getId();
        idUser = orders.getIdUser();
        idStock = orders.getIdStock();
        stockSymbol = orders.getStockSymbol();
        stockName = orders.getStockName();
        volume = orders.getVolume();
        price = orders.getPrice();
        type = orders.getType();
        status = orders.getStatus();
    }

    public Object tranformaParaObjeto1(User user) {
        return null;
    }

}
