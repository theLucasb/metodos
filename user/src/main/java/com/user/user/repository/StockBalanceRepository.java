package com.user.user.repository;

import java.util.List;

import com.user.user.Model.User;
import com.user.user.Model.UserStockBalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockBalanceRepository extends JpaRepository<UserStockBalance, Long> {

    // Optional<UserStockBalance> findByIdUser(IdUserStocks userStock);

    // UserStockBalance findByIdUserAndIdStock(User idUser, Long idStock);

    // confere se o usuario tem a açao pra ser vendida, consequentemente abre a
    // ordem
    @Query(value = "Select * from user_stock_balances where id_user = 1 and id_stock = 1", nativeQuery = true)
    UserStockBalance findByIdUserAndIdStock(User user, Long id_stock);

    @Query(value = " select * from user_stock_balances usb where id_user = ?1 ", nativeQuery = true)
    List<UserStockBalance> findUser(Long user);

}
