package com.user.user.Dto;

import javax.persistence.Column;

import lombok.Data;

@Data
public class IdStockControllerDto {
    private Long id;
    @Column(name = "stock_symbol")
    private String stockSymbol;
    @Column(name = "stock_name")
    private String stockName;

    public IdStockControllerDto(Long id, String stockSymbol, String stockName) {
        this.id = id;
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
    }

    public IdStockControllerDto IdStocks() {
        return new IdStockControllerDto(id, stockSymbol, stockName);
    }
}
