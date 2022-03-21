package com.user.user.services;

//import com.user.user.Dto.OrdersDto;
import com.user.user.Model.Orders;
import com.user.user.repository.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class OrdersServices {

    @Autowired
    WebClient webClient;

    @Autowired
    OrdersRepository ordersRepository;

    @GetMapping
    public Orders addOrders(@PathVariable Long id_stock, @RequestHeader("Authorization") String acessToken) {
        Mono<Orders> monoOrders = this.webClient
                .method(HttpMethod.GET)
                .uri("/stock/{id_stock}", id_stock)
                .header(HttpHeaders.AUTHORIZATION, acessToken)
                .retrieve()
                .bodyToMono(Orders.class);
        monoOrders.subscribe(s -> {
        });
        Orders orders = monoOrders.block();
        return orders;
    }

    // public OrdersDto save(OrdersDto ordersDto) {
    // return ordersRepository.save(ordersDto);
    // }

}
