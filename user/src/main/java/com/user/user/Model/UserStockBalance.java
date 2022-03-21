package com.user.user.Model;

import java.sql.*;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_stock_balances")
public class UserStockBalance {

    @EmbeddedId
    private IdUserStocks userStock;
    @Column(name = "stock_symbol")
    private String stockSymbol;
    @Column(name = "stock_name")
    private String stockName;
    private Integer volume;
    @UpdateTimestamp
    @Column(name = "created_on")
    private Timestamp createdOn;
    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updatedOn;

    public UserStockBalance(IdUserStocks userStock, String stockSymbol, String stockName, Integer volume) {
        this.userStock = userStock;
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
        this.volume = volume;
        this.createdOn = Timestamp.valueOf(LocalDateTime.now());
        this.updatedOn = Timestamp.valueOf(LocalDateTime.now());

    }

}
