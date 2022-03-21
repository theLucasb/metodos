package com.user.user.services;

import com.user.user.Dto.IdStockControllerDto;
import com.user.user.Dto.UserStockBalanceDto;
//import com.user.user.Model.Orders;
//import com.user.user.Model.User;
// import com.user.user.Model.UserStockBalance;
import com.user.user.Model.IdUserStocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RestController
public class StockService {

    @Autowired
    WebClient webClient;

    @GetMapping
    public UserStockBalanceDto search(@PathVariable Long id, @RequestHeader("Authorization") String acessToken) {
        Flux<UserStockBalanceDto> fluxStock = this.webClient
                .method(HttpMethod.GET)
                .uri("/stock/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, acessToken)
                .retrieve()
                .bodyToFlux(UserStockBalanceDto.class);
        fluxStock.subscribe(s -> {
        });
        UserStockBalanceDto stock = fluxStock.blockLast();
        return stock;
    }

    // public void testHotChocolate(){
    // Flux<String> chocolateSource =
    // client.get()
    // .uri("/stream/chocolate")
    // .retrieve()
    // .bodyToFlux(String.class)
    // .doOnNext(System.out::println);
    // chocolateSource.blockLast();
    // }

    public IdStockControllerDto add(@PathVariable Long id, @RequestHeader("Authorization") String acessToken) {
        Mono<IdStockControllerDto> monoStock = this.webClient
                .method(HttpMethod.GET)
                .uri("/stock/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, acessToken)
                .retrieve()
                .bodyToMono(IdStockControllerDto.class);
        monoStock.subscribe(s -> {
        });
        IdStockControllerDto stock = monoStock.block();
        return stock;
    }

    public Object findByIdUserStocks(IdUserStocks idUserStocks) {
        return null;
    }

}