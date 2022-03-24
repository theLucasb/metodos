package com.user.user.consumer.controller;

import com.user.user.consumer.dto.StockInfoDto;
import com.user.user.consumer.dto.StockDto;
import com.user.user.consumer.model.StockId;
import com.user.user.consumer.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stocks/{id}")
    public ResponseEntity<StockDto> getStockbyId(@PathVariable Long id, @RequestHeader("Authorization") String token) {

        StockDto stocksDto = this.stockService.stockbyId(id, token);

        return ResponseEntity.ok(stocksDto);
    }

    @GetMapping("/stock-info/{id}")
    public ResponseEntity<StockInfoDto> getInfoStockById(@PathVariable Long id,
            @RequestHeader("Authorization") String token) {

        StockInfoDto stockInfoDto = this.stockService.stockInfoById(id, token);

        return ResponseEntity.ok(stockInfoDto);
    }

    @GetMapping("/stocks")
    public ResponseEntity<StockInfoDto[]> getAllStocks(@RequestHeader("Authorization") String token) {

        StockInfoDto[] stockInfoDtos = this.stockService.getAllStocks(token);

        return ResponseEntity.ok(stockInfoDtos);
    }

    @PostMapping("/update_stocks")
    public ResponseEntity<StockId> updateStockbyId(@RequestHeader("Authorization") String token) {
        return null;

    }

}
