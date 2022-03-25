package com.user.user.consumer.service;

import com.user.user.consumer.dto.StockInfoDto;
import com.user.user.consumer.dto.StockDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StockService {

    @Autowired
    private WebClient webClient;

    public StockDto stockbyId(@PathVariable Long id, @RequestHeader("Authorization") String token) {

        Mono<StockDto> monoStock = this.webClient
                .method(HttpMethod.GET)
                .uri("/stocks/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(StockDto.class);

        return monoStock.block();

    }

    public StockInfoDto stockInfoById(@PathVariable Long id,
            @RequestHeader("Authorization") String token) {

        Mono<StockInfoDto> monoStock = this.webClient
                .method(HttpMethod.GET)
                .uri("/stock-info/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(StockInfoDto.class);

        return monoStock.block();

    }

    public StockInfoDto[] getAllStocks(@RequestHeader("Authorization") String token) {

        Mono<StockInfoDto[]> monoStock = this.webClient
                .method(HttpMethod.GET)
                .uri("/stocks")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(StockInfoDto[].class);

        return monoStock.block();

    }

    public void updateStockbyPrice(StockDto stocksDto, String token) {
        Mono<StockDto> monoStockPrice = this.webClient
                .method(HttpMethod.POST)
                .uri("/update_stocks")
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(Mono.just(stocksDto), StockDto.class)
                .retrieve()
                .bodyToMono(StockDto.class);

        monoStockPrice.block();
    }

}
