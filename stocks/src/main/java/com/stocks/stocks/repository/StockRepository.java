package com.stocks.stocks.repository;

// import java.util.List;
// import java.util.Optional;

// import javax.transaction.Transactional;

import com.stocks.stocks.model.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // Optional<Stock> stockSymbol(String stockSymbol);

    // List<Stock> findByName(String stock_name);

}
