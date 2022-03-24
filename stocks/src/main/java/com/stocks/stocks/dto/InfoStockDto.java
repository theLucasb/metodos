package com.stocks.stocks.dto;

import com.stocks.stocks.model.Stock;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InfoStockDto {

    private Long id;
    private String stockSymbol;
    private String stockName;

    public InfoStockDto() {
    }

    public InfoStockDto(Stock stock) {
        this.id = stock.getId();
        this.stockSymbol = stock.getStockSymbol();
        this.stockName = stock.getStockName();
    }
}
