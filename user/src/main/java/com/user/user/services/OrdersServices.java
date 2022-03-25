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

    public Orders atualizarStatus(Long id, Integer status) throws NotFoundException {
        Optional<Orders> orders = ordersRepository.findById(id);
        if (orders.isPresent()) {
            orders.get().setStatus(status);
            ordersRepository.save(orders.get());
            return orders.get();
        } else {
            throw new NotFoundException("ORDER_NOT_FOUND");
        }
    }

    public void confereRemaining(Long idCompra, Long idVenda, Long volumeCompra, Long volumeVenda) {
        Orders orderCompra = ordersRepository.findById(idCompra).orElseThrow();
        Orders orderVenda = ordersRepository.findById(idVenda).orElseThrow();
        var volumeRemainingCompra = orderCompra.getVolumeRemaining();
        var volumeRemainingVenda = orderVenda.getVolumeRemaining();
        if (volumeCompra > volumeVenda) {
            volumeRemainingCompra = volumeRemainingCompra - volumeVenda;
            volumeRemainingVenda = volumeRemainingVenda - volumeVenda;
            orderCompra.setVolumeRemaining(volumeRemainingCompra);
            orderVenda.setVolumeRemaining(volumeRemainingVenda);
            if (volumeRemainingVenda == 0) {
                orderVenda.setStatus(2);
            }
            if (volumeRemainingCompra == 0) {
                orderCompra.setStatus(2);
            }
            ordersRepository.save(orderCompra);
            ordersRepository.save(orderVenda);
        }
        if (volumeVenda > volumeCompra) {
            volumeRemainingVenda = volumeRemainingVenda - volumeCompra;
            volumeRemainingCompra = volumeRemainingCompra - volumeCompra;
            orderCompra.setVolumeRemaining(volumeRemainingCompra);
            orderVenda.setVolumeRemaining(volumeRemainingVenda);
            if (volumeRemainingVenda == 0) {
                orderVenda.setStatus(2);
            }
            if (volumeRemainingCompra == 0) {
                orderCompra.setStatus(2);
            }
            ordersRepository.save(orderCompra);
            ordersRepository.save(orderVenda);
        }

    }

    public void atualizarBidAsk(Long idStock, String stockName, String stockSymbol, String token) {
        Double bidMin = ordersRepository.findByBidMin(idStock);
        Double bidMax = ordersRepository.findByBidMax(idStock);
        Double askMin = ordersRepository.findByAskMin(idStock);
        Double askMax = ordersRepository.findByAskMax(idStock);

        StockDto stocksDto = new StockDto(idStock, stockSymbol, stockName,
                bidMin, bidMax, askMin, askMax);

        stockService.updateStockbyPrice(stocksDto, token);

    }

    public void confereBalance(Long idUser, Long volumeCompra, Double priceBuy, Double priceSell) {
        User user = userRepository.findById(idUser).orElseThrow();
        var dollarBalance = user.getDollarBalance();
        if ((priceBuy - priceSell) > 0) {
            var returnValue = (priceBuy - priceSell) * volumeCompra;
            user.setDollarBalance((dollarBalance + returnValue));
            userRepository.save(user);
        }
    }

    public void atualizarDollar(Double price, Long volumeCompra, Long volumeVenda, Long id, int type,
            Double finalBalance) {
        if (type == 1) {
            finalBalance = finalBalance - (price * volumeVenda);
            userRepository.findbySetDollar(id, finalBalance);
        } else if (type == 2) {
            finalBalance = finalBalance + (price * volumeCompra);
            userRepository.findbySetDollar(id, finalBalance);
        }
    }

    public void atualizarStockBalance(User userId, Long stockId, String stockSymbol, String stockName,
            Long volumeCompra,
            Long volumeVenda, int type) {
        if (type == 2) {
            if (volumeVenda > volumeCompra) {
                UserStockBalance userStockBalance = stockBalanceRepository
                        .findById(new IdUserStocks(userId, stockId)).orElseThrow();
                var volume = userStockBalance.getVolume() - volumeCompra;
                userStockBalance.setVolume(volume);
                stockBalanceRepository.save(userStockBalance);
            } else {
                UserStockBalance userStockBalance = stockBalanceRepository
                        .findById(new IdUserStocks(userId, stockId)).orElseThrow();
                var volume = userStockBalance.getVolume() - volumeVenda;
                userStockBalance.setVolume(volume);
                stockBalanceRepository.save(userStockBalance);
            }
        }
        if (type == 1) {
            if (volumeCompra > volumeVenda) {
                var balance = volumeCompra - volumeVenda;
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
                balance = volumeCompra + userStockBalance.getVolume();
                userStockBalance.setVolume(balance);
                stockBalanceRepository.save(userStockBalance);
            }

        }

    }

    public OrdersDto salvarCompra(OrdersDto ordersDto, String token) {
        User user = userRepository.findById(ordersDto.getIdUser()).orElseThrow();
        var totalAmount = ordersDto.getPrice() * ordersDto.getVolume();
        if (totalAmount <= user.getDollarBalance()) {
            Orders orderBuy = ordersRepository.save(ordersDto.buscarDto(user));

            // retem o valor do usuario mesmo antes da ordem fechar
            var balance = user.getDollarBalance() - totalAmount;
            user.setDollarBalance(balance);
            userRepository.save(user);

            // atualiza o bid min/bid max
            atualizarBidAsk(ordersDto.getIdStock(), ordersDto.getStockSymbol(),
                    ordersDto.getStockName(),
                    token);

            // busca uma ordem equivalente
            List<Orders> orders1 = ordersRepository.findByStockAndTypeOrderAndIdUser(
                    ordersDto.getIdStock(),
                    ordersDto.getType(), ordersDto.getIdUser());

            for (Orders orderVenda : orders1) {

                // compara o volume da ordem do tipo compra com o volume da ordem do tipo compra
                // se o valor da ordem do tipo compra for maior ou igual
                if ((ordersDto.getPrice() >= orderVenda.getPrice())) {
                    confereRemaining(orderBuy.getId(), orderVenda.getId(), orderBuy.getVolume(),
                            orderVenda.getVolume());
                    // pega o saldo
                    double finalBalanceOrder = orderVenda.getUsers().getDollarBalance();
                    // atualiza o stock balance de quem comprou
                    atualizarStockBalance(user, orderBuy.getIdStock(), orderBuy.getStockSymbol(),
                            orderBuy.getStockName(),
                            orderBuy.getVolume(), orderVenda.getVolume(), orderBuy.getType());
                    // atualiza o dollar balance de quem vendeu
                    atualizarDollar(orderVenda.getPrice(), orderVenda.getVolume(), orderVenda.getVolume(),
                            orderVenda.getUsers().getId(),
                            orderVenda.getType(), finalBalanceOrder);
                }

            }
            return ordersDto;
        }
        return ordersDto;
    }

    public OrdersDto salvarVenda(OrdersDto ordersDto, String token) {
        User user = userRepository.findById(ordersDto.getIdUser()).orElseThrow();
        UserStockBalance userStockBalance = stockBalanceRepository
                .findById(new IdUserStocks(user, ordersDto.getIdStock())).orElseThrow();
        if (ordersDto.getVolume() <= userStockBalance.getVolume()) {

            // salva a ordem
            Orders orderVenda = ordersRepository.save(ordersDto.buscarDto(user));

            // retem o dinheiro
            var volume = userStockBalance.getVolume() - ordersDto.getVolume();
            userStockBalance.setVolume(volume);
            stockBalanceRepository.save(userStockBalance);

            // atualiza o bid ask/ask max
            atualizarBidAsk(ordersDto.getIdStock(), ordersDto.getStockSymbol(),
                    ordersDto.getStockName(),
                    token);

            // encontra a ordem compativel
            List<Orders> orders1 = ordersRepository.findByStockAndTypeOrderAndIdUser(
                    ordersDto.getIdStock(),
                    ordersDto.getType(), ordersDto.getIdUser());

            for (Orders orderBuy : orders1) {
                if (orderVenda.getPrice() <= orderBuy.getPrice()) {
                    confereRemaining(orderBuy.getId(), orderVenda.getId(), orderBuy.getVolume(),
                            orderVenda.getVolume());
                    double finalBalance1 = orderVenda.getUsers().getDollarBalance();

                    atualizarStockBalance(orderBuy.getUsers(), orderBuy.getIdStock(), orderBuy.getStockSymbol(),
                            orderBuy.getStockName(), orderBuy.getVolume(), orderVenda.getVolume(), orderBuy.getType());
                    atualizarDollar(ordersDto.getPrice(), ordersDto.getVolume(), ordersDto.getVolume(),
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
            salvarCompra(ordersDto, token);
        } else if (ordersDto.getType() == 2) {
            salvarVenda(ordersDto, token);
        }
        return ordersDto;
    }

    public Page<Orders> findByOrdersPage(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ordersRepository.findAll(pageable);
    }

    public Page<Orders> findByIdOrdersPage(Long id, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ordersRepository.findByIdPageable(pageable, id);
    }
}
