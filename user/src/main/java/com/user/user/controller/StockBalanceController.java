package com.user.user.controller;

import java.util.List;

import com.user.user.Dto.UserStockBalanceDto;
import com.user.user.Model.User;
import com.user.user.Model.UserStockBalance;
import com.user.user.repository.StockBalanceRepository;
import com.user.user.repository.UserRepository;
import com.user.user.services.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stockbalance")
public class StockBalanceController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StockBalanceRepository stockBalanceRepository;

    @Autowired
    StockService stockBalanceServices;

    @GetMapping
    public List<UserStockBalance> listar() {
        return stockBalanceRepository.findAll();
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<UserStockBalanceDto> search(@PathVariable Long id,
            @RequestHeader("Authorization") String acessToken) {
        System.out.println(acessToken);
        UserStockBalanceDto userStockBalance = stockBalanceServices.search(id, acessToken);
        return ResponseEntity.ok(userStockBalance);
    }

    // @GetMapping("/user/{id}")

    // @GetMapping("/{id_user}")
    // public ResponseEntity<Optional> findByIdUser(
    // @PathVariable("id_user") IdUserStocks userStocks) {
    // return
    // ResponseEntity.ok().body(stockBalanceServices.findByIdUser(userStock));
    // }

    // @GetMapping("/teste/{id}")
    // public ResponseEntity<IdStockControllerDto> add(@PathVariable Long id,
    // @RequestHeader("Authorization") String acessToken) {
    // IdStockControllerDto teste = this.stockBalanceServices.add(id, acessToken);
    // return ResponseEntity.ok(teste);
    // }

    // @GetMapping("/find")
    // public UserStockBalance find(@RequestBody OrdersDto ordersDto) {
    // return stockBalanceRepository.findByIdUserAndIdStock(ordersDto.getIdUser(),
    // ordersDto.getIdStock());
    // }

    @PostMapping("/add")
    public ResponseEntity<UserStockBalance> add(@RequestBody UserStockBalanceDto stock_Balance) {
        User user = userRepository.findById(stock_Balance.getIdStock()).orElseThrow();
        UserStockBalance userBalance = stockBalanceRepository.save(stock_Balance.userStockObj(user));
        return new ResponseEntity<>(userBalance, HttpStatus.CREATED);
    }

    @GetMapping("/teste/{id_user}")
    public List<UserStockBalance> list(@PathVariable("id_user") Long user,
            @RequestHeader("Authorization") String acessToken) {
        System.out.println(acessToken);
        {
            return stockBalanceRepository.findUser(user);
        }
    }
};
