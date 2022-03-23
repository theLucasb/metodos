package com.user.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.user.user.Model.IdUserStocks;
import com.user.user.Model.UserStockBalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface StockBalanceRepository extends JpaRepository<UserStockBalance, IdUserStocks> {
    UserStockBalance findAllById(IdUserStocks idUserStocks);

    @Query(value = "SELECT * from user_stock_balances where id_user = :id_user", nativeQuery = true)
    List<UserStockBalance> findAllByIdUser(@Param("id_user") Long idUser);

    @Query(value = " select * from user_stock_balances usb where id_user = ?1 ", nativeQuery = true)
    List<UserStockBalance> findUser(Long user);

}
