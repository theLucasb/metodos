package com.stocks.stocks.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "market_cap")
    private Long marketCap;
    @Column(name = "stock_symbol")
    private String stockSymbol;
    @Column(name = "stock_name")
    private String stockName;
    @Column(name = "ask_min")
    private Double askMin;
    @Column(name = "ask_max")
    private Double askMax;
    @Column(name = "bid_min")
    private Double bidMin;
    @Column(name = "bid_max")
    private Double bidMax;
    @UpdateTimestamp
    @Column(name = "created_on")
    private Timestamp createdOn;
    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updatedOn;

    public Stock(Long id, Double bidMin, Double bidMax) {
        this.id = id;
        this.bidMin = bidMin;
        this.bidMax = bidMax;
    }

    public Stock(Long id, String stockSymbol, String stockName, Double bidMin, Double bidMax, Double askMin,
            Double askMax) {
    }

}
