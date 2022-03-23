package com.user.user.Model;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_stock_balances")
public class UserStockBalance implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IdUserStocks id;

    @Column(name = "stock_symbol")
    private String stockSymbol;

    @Column(name = "stock_name")
    private String stockName;

    private Long volume;

    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updatedOn;

    public UserStockBalance(IdUserStocks id, String stockSymbol, String stockName, Long volume) {
        this.id = id;

        this.stockSymbol = stockSymbol;

        this.stockName = stockName;

        this.volume = volume;

        this.createdOn = Timestamp.valueOf(LocalDateTime.now());
        this.updatedOn = Timestamp.valueOf(LocalDateTime.now());
    }
}
