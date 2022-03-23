package com.user.user.controller;

import com.user.user.Dto.OrdersDto;
import com.user.user.Dto.UpdateOrderDto;
import com.user.user.Model.Orders;
import com.user.user.handleerror.NotFoundException;

import com.user.user.services.OrdersServices;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:8081/")
@RestController
@RequiredArgsConstructor
@RequestMapping
public class OrdersController {

    private final OrdersServices ordersServices;

    @GetMapping("/orders")
    public Page<Orders> listaPedidos(@RequestParam int pageSize, @RequestParam int pageNumber) {
        return ordersServices.findOrdersPage(pageSize, pageNumber);
    }

    @GetMapping("/orders/{id}")
    public Page<Orders> listaOrdemPorId(@PathVariable Long id, @RequestParam int pageNumber, int pageSize) {
        return ordersServices.findOrdersPageById(id, pageSize, pageNumber);
    }

    @PostMapping("/order-update/{status}")
    public ResponseEntity<Orders> updateOrder(@RequestBody UpdateOrderDto dto,
            @PathVariable("status") Integer status, @RequestHeader("Authorization") String token)
            throws NotFoundException {
        try {
            return ResponseEntity.ok().body(ordersServices.updateStatus(dto.getId(), status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/new_order")
    public ResponseEntity<OrdersDto> salvar(@RequestBody OrdersDto dto,
            @RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.ok().body(ordersServices.salvar(dto, token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
