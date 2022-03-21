package com.user.user.controller;

import com.user.user.Dto.OrdersDto;
import com.user.user.Model.Orders;

import com.user.user.services.BuySaleService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BuyAndSaleController {

    @Autowired
    private BuySaleService buySaleService;

    @PostMapping("/buysale")
    public OrdersDto buysale(@RequestBody Orders order) {

        return buySaleService.buysale(order);
    }

}