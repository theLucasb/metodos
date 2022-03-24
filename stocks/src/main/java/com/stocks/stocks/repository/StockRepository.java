package com.stocks.stocks.repository;

import java.util.List;

import com.stocks.stocks.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query(value = "SELECT * FROM stocks ORDER BY updated_on asc", nativeQuery = true)
    List<Stock> findUpdateAllOrder();

}
