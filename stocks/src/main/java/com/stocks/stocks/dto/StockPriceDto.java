// package com.stocks.stocks.dto;

// import java.sql.Timestamp;
// import java.time.LocalDateTime;

// import com.stocks.stocks.model.Stock;

// import org.hibernate.annotations.UpdateTimestamp;

// public class StockPriceDto {
// private Long id;
// private String stockSymbol;
// private String stockName;
// private Double bidMin;
// private Double bidMax;
// private Double askMin;
// private Double askMax;
// @UpdateTimestamp
// private Timestamp updatedOn;

// public StockPriceDto() {
// this.updatedOn = Timestamp.valueOf(LocalDateTime.now());
// }

// public Stock pegarModel() {
// Stock stocks = new Stock();
// stocks.setId(this.id);
// stocks.setStockSymbol(this.stockSymbol);
// stocks.setStockName(this.stockName);
// stocks.setBidMin(this.bidMin);
// stocks.setBidMax(this.bidMax);
// stocks.setAskMin(this.askMin);
// stocks.setAskMax(this.askMax);
// return stocks;
// }
// }
