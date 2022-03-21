package com.user.user.controller;

import java.util.List;

//import javax.persistence.criteria.Order;

//import javax.persistence.criteria.Order;

import com.user.user.Dto.OrdersDto;
import com.user.user.Model.Orders;
import com.user.user.repository.OrdersRepository;
import com.user.user.services.BuySaleService;
import com.user.user.services.OrdersServices;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrdersServices ordersServices;

    @Autowired
    BuySaleService buySaleService;

    @GetMapping
    public List<Orders> listaOrders() {
        return ordersRepository.findAll();
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Orders> addOrders(){
    // return ordersServices.ok();
    // }

    @GetMapping("/{id_stock}")
    public ResponseEntity<Orders> addOrders(@PathVariable Long id_stock,
            @RequestHeader("Authorization") String acessToken) {
        Orders orders = ordersServices.addOrders(id_stock, acessToken);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/type") // retorna lista de ordens
    public List<Orders> typeList(@RequestHeader("Authorization") String acesstoken, @RequestBody OrdersDto ordersDto) {
        return ordersRepository.findByType(ordersDto.getIdStock());
    }

    // @PostMapping("/posttype")
    // public ResponseEntity<Orders> postList(@PathVariable Long id_stock,
    // @RequestBody OrdersDto ordersDto) {
    // OrdersDto orders = this.ordersServices.update(ordersDto);
    // return orders.update(orders);
    // }

    // @PostMapping
    // public ResponseEntity<Orders> update(Long id) {
    // Orders ordersList = this.ordersRepository.update(id);
    // return new ResponseEntity<>(ordersList, HttpStatus.CREATED);
    // }

    @PostMapping("/comprar") // cria nova ordem na mao
    public Orders add(@RequestBody Orders orders) {
        return ordersRepository.save(orders);// salva no banco
    }

    // @PostMapping
    // public Orders select(@RequestBody Orders orders){
    // return userStockBalance.save(orders);
    // }

    @DeleteMapping
    public void delete(@RequestBody Orders orders) {
        ordersRepository.delete(orders);
    }

}
