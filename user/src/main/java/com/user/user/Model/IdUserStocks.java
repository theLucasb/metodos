package com.user.user.Model;

import java.io.Serializable;
import java.util.Objects;

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

    public void setUsers(User user) {
        this.user = user;
    }

    public Long getIdStock() {
        return idStock;
    }

    public void setIdStock(Long idStock) {
        this.idStock = idStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        IdUserStocks that = (IdUserStocks) o;
        return Objects.equals(user, that.user) && Objects.equals(idStock, that.idStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, idStock);
    }
}