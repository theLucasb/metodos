package com.user.user.repository;

import com.user.user.Model.UserStockBalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockBalanceDtoRepository extends JpaRepository<UserStockBalance, Long> {
    // UserStockBalanceDto findByIdUser(IdUserStocks userStock);;

}
