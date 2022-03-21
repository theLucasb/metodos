package com.stocks.stocks.controller;

import java.util.List;
import java.util.Optional;

import com.stocks.stocks.model.Stock;
import com.stocks.stocks.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping

    public List<Stock> listarStock() {
        return stockRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Stock> listarStock(@PathVariable Long id, @RequestHeader("Authorization") String acessToken) {
        return stockRepository.findById(id);
    }
    
    @PostMapping
    public Stock add(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }

    @DeleteMapping
    public void delete(@RequestBody Stock stock){
        stockRepository.delete(stock);
    }

    @PutMapping
    public Stock update(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }
}
