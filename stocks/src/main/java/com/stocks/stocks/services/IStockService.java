package com.stocks.stocks.services;

import java.util.Optional;

import com.stocks.stocks.model.Stock;

public interface IStockService {
    Optional<Stock> stockSymbol(String stockSymbol);

}
