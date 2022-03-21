package com.user.user.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class IdUserStocks implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @Column(name = "id_stock")
    private Long idStock;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdStock() {
        return idStock;
    }

    public void setIdStock(Long idStock) {
        this.idStock = idStock;
    }

}