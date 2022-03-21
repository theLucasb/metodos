package com.stocks.stocks.dto;

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

}
