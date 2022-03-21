package com.user.user.services;

import com.user.user.Model.UserStockBalance;
import com.user.user.repository.StockBalanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStockBalanceServices {

    @Autowired
    private StockBalanceRepository stockBalanceRepository;

    public UserStockBalance salvar(UserStockBalance userStockBalance) {
        return stockBalanceRepository.save(userStockBalance);
    }

}
