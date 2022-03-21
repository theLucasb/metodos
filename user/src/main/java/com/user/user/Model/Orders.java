package com.user.user.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User idUser;

    @Column(name = "id_stock")
    private Long idStock;

    @Column(name = "stock_symbol")
    private String stockSymbol;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "price")
    private Double price;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    @UpdateTimestamp
    @Column(name = "created_on")
    private Timestamp createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updatedOn;

    public Orders(Long id, User user, Long idStock, String stockSymbol, String stockName, Long volume,
            double price, Integer type, Integer status) {
        this.id = id;
        this.idUser = user;
        this.idStock = idStock;
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
        this.volume = volume;
        this.price = price;
        this.type = type;
        this.status = status;

    }

    // public Orders() {
    // this.created_on = Timestamp.valueOf(LocalDateTime.now());
    // this.updated_on = Timestamp.valueOf(LocalDateTime.now());
    // }

}
