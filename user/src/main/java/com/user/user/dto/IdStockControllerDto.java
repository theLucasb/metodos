package com.user.user.dto;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdStockControllerDto {
    private Long id;
    @Column(name = "stock_symbol")
    private String stockSymbol;
    @Column(name = "stock_name")
    private String stockName;

    public IdStockControllerDto IdStocks() {
        return new IdStockControllerDto(id, stockSymbol, stockName);
    }
}
