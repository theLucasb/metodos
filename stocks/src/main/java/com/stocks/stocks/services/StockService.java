// package com.stocks.stocks.services;

// import java.util.List;
// import java.util.Optional;

// import com.stocks.stocks.controller.StockController;
// import com.stocks.stocks.model.Stock;
// import com.stocks.stocks.repository.StockRepository;

// import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class StockService implements IStockService {
// private final StockRepository stockRepository;

// @Override
// public Optional<Stock> stockSymbol(String stockSymbol) {
// return stockRepository.stockSymbol(stockSymbol);
// }

// public List<Stock> getStock(String stock_name) throws Exception {
// List<Stock> stock = stockRepository.findByName(stock_name);
// return stock;
// }

// public Object stockUnico(long l) {
// return null;
// }

// public void standaloneSetup(StockController stockController) {
// }

// }