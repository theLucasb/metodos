package com.user.user.services;

import com.user.user.Dto.OrdersDto;
import com.user.user.Model.IdUserStocks;
import com.user.user.Model.Orders;
import com.user.user.Model.User;
import com.user.user.Model.UserStockBalance;
import com.user.user.consumer.dto.StockDto;
import com.user.user.consumer.service.StockService;
import com.user.user.handleerror.NotFoundException;
import com.user.user.repository.OrdersRepository;
import com.user.user.repository.StockBalanceRepository;
import com.user.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServices {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    StockService stockService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StockBalanceRepository stockBalanceRepository;

    public Orders updateStatus(Long id, Integer status) throws NotFoundException {
        Optional<Orders> orders = ordersRepository.findById(id);
        if (orders.isPresent()) {
            orders.get().setStatus(status);
            ordersRepository.save(orders.get());
            return orders.get();
        } else {
            throw new NotFoundException("ORDER_NOT_FOUND");
        }
    }

    public void checkVolumeRemaining(Long idBuy, Long idSell, Long volumeBuy, Long volumeSell) {
        Orders userOrderBuy = ordersRepository.findById(idBuy).orElseThrow();
        Orders ordersell = ordersRepository.findById(idSell).orElseThrow();
        var volumeRemainingBuy = userOrderBuy.getVolumeRemaining();
        var volumeRemainingSell = ordersell.getVolumeRemaining();
        if (volumeBuy > volumeSell) {
            volumeRemainingBuy = volumeRemainingBuy - volumeSell;
            volumeRemainingSell = volumeRemainingSell - volumeSell;
            userOrderBuy.setVolumeRemaining(volumeRemainingBuy);
            ordersell.setVolumeRemaining(volumeRemainingSell);
            if (volumeRemainingSell == 0) {
                ordersell.setStatus(2);
            }
            if (volumeRemainingBuy == 0) {
                userOrderBuy.setStatus(2);
            }
            ordersRepository.save(userOrderBuy);
            ordersRepository.save(ordersell);
        }
        if (volumeSell > volumeBuy) {
            volumeRemainingSell = volumeRemainingSell - volumeBuy;
            volumeRemainingBuy = volumeRemainingBuy - volumeBuy;
            userOrderBuy.setVolumeRemaining(volumeRemainingBuy);
            ordersell.setVolumeRemaining(volumeRemainingSell);
            if (volumeRemainingSell == 0) {
                ordersell.setStatus(2);
            }
            if (volumeRemainingBuy == 0) {
                userOrderBuy.setStatus(2);
            }
            ordersRepository.save(userOrderBuy);
            ordersRepository.save(ordersell);
        }
    }

    public void updateStockPrice(Long idStock, String stockName, String stockSymbol, String token) {
        Double bidMin = ordersRepository.findByIdStockMinPriceBid(idStock);
        Double bidMax = ordersRepository.findByIdStockMaxPriceBid(idStock);
        Double askMin = ordersRepository.findByIdStockMinPriceAsk(idStock);
        Double askMax = ordersRepository.findByIdStockMaxPriceAsk(idStock);

        StockDto stocksDto = new StockDto(idStock, stockSymbol, stockName,
                bidMin, bidMax, askMin, askMax);

        stockService.updateStockbyPrice(stocksDto, token);

    }

    public void checkReturnBallance(Long idUser, Long volumeBuy, Double priceBuy, Double priceSell) {
        User user = userRepository.findById(idUser).orElseThrow();
        var dollarBalance = user.getDollarBalance();
        if ((priceBuy - priceSell) > 0) {
            var returnValue = (priceBuy - priceSell) * volumeBuy;
            user.setDollarBalance((dollarBalance + returnValue));
            userRepository.save(user);
        }
    }

    public void updateDollarBalance(Double price, Long volumeBuy, Long volumeSell, Long id, int type,
            Double finalBalance) {
        if (type == 1) {
            finalBalance = finalBalance - (price * volumeSell);
            userRepository.findbyIdSetDollarBalance(id, finalBalance);
        } else if (type == 2) {
            finalBalance = finalBalance + (price * volumeBuy);
            userRepository.findbyIdSetDollarBalance(id, finalBalance);
        }
    }

    public void updateStockBalance(User userId, Long stockId, String stockSymbol, String stockName, Long volumeBuy,
            Long volumeSell, int type) {
        if (type == 2) {
            if (volumeSell > volumeBuy) {
                UserStockBalance userStockBalance = stockBalanceRepository
                        .findById(new IdUserStocks(userId, stockId)).orElseThrow();
                var volume = userStockBalance.getVolume() - volumeBuy;
                userStockBalance.setVolume(volume);
                stockBalanceRepository.save(userStockBalance);
            } else {
                UserStockBalance userStockBalance = stockBalanceRepository
                        .findById(new IdUserStocks(userId, stockId)).orElseThrow();
                var volume = userStockBalance.getVolume() - volumeSell;
                userStockBalance.setVolume(volume);
                stockBalanceRepository.save(userStockBalance);
            }
        }
        if (type == 1) {
            if (volumeBuy > volumeSell) {
                var balance = volumeBuy - volumeSell;
                Optional<UserStockBalance> userStockBalance = stockBalanceRepository
                        .findById(new IdUserStocks(userId, stockId));
                if (userStockBalance.isPresent()) {
                    balance = userStockBalance.get().getVolume() + balance;
                    userStockBalance.get().setVolume(balance);
                    stockBalanceRepository.save(userStockBalance.get());
                } else {
                    stockBalanceRepository.save(new UserStockBalance(new IdUserStocks(userId, stockId),
                            stockSymbol, stockName, balance));
                }
            } else {
                int initializer = 0;
                var balance = Long.valueOf(initializer);
                UserStockBalance userStockBalance = stockBalanceRepository
                        .findById(new IdUserStocks(userId, stockId)).orElse(
                                stockBalanceRepository.save(new UserStockBalance(
                                        new IdUserStocks(userId, stockId), stockSymbol, stockName, balance)));
                balance = volumeBuy + userStockBalance.getVolume();
                userStockBalance.setVolume(balance);
                stockBalanceRepository.save(userStockBalance);
            }

        }
    }

    public OrdersDto saveBuy(OrdersDto ordersDto, String token) {
        User user = userRepository.findById(ordersDto.getIdUser()).orElseThrow();
        var totalAmount = ordersDto.getPrice() * ordersDto.getVolume();
        if (totalAmount <= user.getDollarBalance()) {
            Orders orderBuy = ordersRepository.save(ordersDto.transformaParaObjeto(user));

            // retem o valor do usuario mesmo antes da ordem fechar
            var balance = user.getDollarBalance() - totalAmount;
            user.setDollarBalance(balance);
            userRepository.save(user);

            // atualiza o bid min/bid max
            updateStockPrice(ordersDto.getIdStock(), ordersDto.getStockSymbol(),
                    ordersDto.getStockName(),
                    token);

            // busca uma ordem equivalente
            List<Orders> orders1 = ordersRepository.findByStockAndTypeOrderAndIdUser(
                    ordersDto.getIdStock(),
                    ordersDto.getType(), ordersDto.getIdUser());

            for (Orders orderSell : orders1) {

                // compara o volume da ordem do tipo compra com o volume da ordem do tipo compra
                // se o valor da ordem do tipo compra for maior ou igual
                if ((ordersDto.getPrice() >= orderSell.getPrice())) {
                    checkVolumeRemaining(orderBuy.getId(), orderSell.getId(), orderBuy.getVolume(),
                            orderSell.getVolume());
                    // pega o saldo
                    double finalBalanceOrder = orderSell.getUsers().getDollarBalance();
                    // atualiza o stock balance de quem comprou
                    updateStockBalance(user, orderBuy.getIdStock(), orderBuy.getStockSymbol(), orderBuy.getStockName(),
                            orderBuy.getVolume(), orderSell.getVolume(), orderBuy.getType());
                    // atualiza o dollar balance de quem vendeu
                    updateDollarBalance(orderSell.getPrice(), orderSell.getVolume(), orderSell.getVolume(),
                            orderSell.getUsers().getId(),
                            orderSell.getType(), finalBalanceOrder);
                }

            }
            return ordersDto;
        }
        return ordersDto;
    }

    public OrdersDto saveSell(OrdersDto ordersDto, String token) {
        User user = userRepository.findById(ordersDto.getIdUser()).orElseThrow();
        UserStockBalance userStockBalance = stockBalanceRepository
                .findById(new IdUserStocks(user, ordersDto.getIdStock())).orElseThrow();
        if (ordersDto.getVolume() <= userStockBalance.getVolume()) {

            // salva a ordem
            Orders orderSell = ordersRepository.save(ordersDto.transformaParaObjeto(user));

            // retem o dinheiro
            var volume = userStockBalance.getVolume() - ordersDto.getVolume();
            userStockBalance.setVolume(volume);
            stockBalanceRepository.save(userStockBalance);

            // atualiza o bid ask/ask max
            updateStockPrice(ordersDto.getIdStock(), ordersDto.getStockSymbol(),
                    ordersDto.getStockName(),
                    token);

            // encontra a ordem compativel
            List<Orders> orders1 = ordersRepository.findByStockAndTypeOrderAndIdUser(
                    ordersDto.getIdStock(),
                    ordersDto.getType(), ordersDto.getIdUser());

            for (Orders orderBuy : orders1) {
                if (orderSell.getPrice() <= orderBuy.getPrice()) {
                    checkVolumeRemaining(orderBuy.getId(), orderSell.getId(), orderBuy.getVolume(),
                            orderSell.getVolume());
                    double finalBalance1 = orderSell.getUsers().getDollarBalance();

                    updateStockBalance(orderBuy.getUsers(), orderBuy.getIdStock(), orderBuy.getStockSymbol(),
                            orderBuy.getStockName(), orderBuy.getVolume(), orderSell.getVolume(), orderBuy.getType());
                    updateDollarBalance(ordersDto.getPrice(), ordersDto.getVolume(), ordersDto.getVolume(),
                            ordersDto.getIdUser(),
                            ordersDto.getType(), finalBalance1);
                }
            }
            return ordersDto;
        }
        return ordersDto;
    }

    public OrdersDto salvar(OrdersDto ordersDto, String token) {
        if (ordersDto.getType() == 1) {
            saveBuy(ordersDto, token);
        } else if (ordersDto.getType() == 2) {
            saveSell(ordersDto, token);
        }
        return ordersDto;
    }

    public Page<Orders> findOrdersPage(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ordersRepository.findAll(pageable);
    }

    public Page<Orders> findOrdersPageById(Long id, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ordersRepository.findByIdPageable(pageable, id);
    }
}
