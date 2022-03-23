package com.stocks.stocks.dto;

import com.stocks.stocks.model.Stock;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StockInfoDto {

    private Long id;
    private String stockSymbol;
    private String stockName;

    public StockInfoDto() {
    }

    public StockInfoDto(Stock stock) {
        this.id = stock.getId();
        this.stockSymbol = stock.getStockSymbol();
        this.stockName = stock.getStockName();
    }
}
