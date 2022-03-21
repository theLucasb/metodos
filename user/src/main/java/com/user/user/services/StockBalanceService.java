// package com.user.user.services;

// import java.util.Optional;

// import com.user.user.Dto.UserStockBalanceDto;
// import com.user.user.Model.IdUserStocks;
// import com.user.user.Model.UserStockBalance;
// import com.user.user.repository.StockBalanceRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class StockBalanceService implements IStockBalanceService {
// @Autowired
// StockBalanceRepository stockBalanceRepository;

// public Optional<UserStockBalanceDto> findByIdUser(IdUserStocks userStock);

// {
// Optional<UserStockBalance> userStockBalance =
// stockBalanceRepository.findByIdUser(userStock);
// Optional<UserStockBalanceDto> userStockBalanceDto = userStockBalance.map(dto
// -> new UserDto(dto));
// return userStockBalanceDto;

// }
// }
