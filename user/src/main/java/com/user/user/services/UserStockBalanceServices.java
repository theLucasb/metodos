package com.user.user.services;

import java.util.List;
import java.util.Optional;

import com.user.user.dto.UserStockBalanceDto;
import com.user.user.model.IdUserStocks;
import com.user.user.model.User;
import com.user.user.model.UserStockBalance;
import com.user.user.repository.StockBalanceRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserStockBalanceServices {

    private final StockBalanceRepository stockBalanceRepository;

    public UserStockBalance salvar(UserStockBalance userStockBalance) {
        return stockBalanceRepository.save(userStockBalance);
    }

    public Optional<UserStockBalance> findById(User user, Long idStock) {
        return stockBalanceRepository.findById(new IdUserStocks(user, idStock));
    }

    public List<UserStockBalanceDto> listByIdUser(Long id) {
        return stockBalanceRepository.findAllByIdUser(id).stream().map(UserStockBalanceDto::new).toList();
    }

    public List<UserStockBalance> findAll() {
        return null;
    }

}
