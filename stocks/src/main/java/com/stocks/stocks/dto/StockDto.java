package com.stocks.stocks.dto;

import com.stocks.stocks.model.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StockDto {
    private Long id;
    private String stockSymbol;
    private String stockName;
    private Double askMin;
    private Double askMax;
    private Double bidMin;
    private Double bidMax;

    public Stock entity() {
        Stock stocks = new Stock();
        stocks.setId(this.id);
        stocks.setStockSymbol(this.stockSymbol);
        stocks.setStockName(this.stockName);
        stocks.setBidMin(this.bidMin);
        stocks.setBidMax(this.bidMax);
        stocks.setAskMin(this.askMin);
        stocks.setAskMax(this.askMax);
        return stocks;
    }

}
