package com.user.user.controller;

import java.util.List;

import com.user.user.Dto.UserStockBalanceDto;
import com.user.user.Model.User;
import com.user.user.Model.UserStockBalance;
import com.user.user.repository.StockBalanceRepository;
import com.user.user.repository.UserRepository;
import com.user.user.services.UserStockBalanceServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:8081/")
@RestController
@RequiredArgsConstructor
@RequestMapping
public class StockBalanceController {

    @Autowired
    StockBalanceRepository stockBalanceRepository;

    @Autowired
    UserRepository userRepository;

    private final UserStockBalanceServices userStockBalanceServices;

    @GetMapping("/user_stock/{id}")
    public List<UserStockBalanceDto> listaUserStock(@PathVariable Long id) {
        return userStockBalanceServices.listByIdUser(id);
    }

    @GetMapping("/teste/{id_user}")
    public List<UserStockBalance> list(@PathVariable("id_user") Long user,
            @RequestHeader("Authorization") String acessToken) {
        System.out.println(acessToken);
        {
            return stockBalanceRepository.findUser(user);
        }
    }

    @GetMapping("/user_stock")
    public List<UserStockBalance> listaUserStock() {
        return userStockBalanceServices.findAll();
    }

    @PostMapping("/new-user-stock")
    public ResponseEntity<UserStockBalance> salvar(@RequestBody UserStockBalanceDto dto) {
        User user = userRepository.findById(dto.getIdUser()).orElseThrow();
        UserStockBalance userStockBalance = userStockBalanceServices.findById(user, dto.getIdStock())
                .orElse(userStockBalanceServices.salvar(dto.transformaParaObjeto(user)));
        return new ResponseEntity<>(userStockBalance, HttpStatus.CREATED);
    }

}
