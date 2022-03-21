package com.user.user.services;

import java.util.List;

import com.user.user.Dto.OrdersDto;
import com.user.user.Model.Orders;
import com.user.user.repository.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class BuySaleService {

    @Autowired
    private OrdersRepository ordersRepository;

    public OrdersDto buysale(Orders orders) {
        int type = orders.getType();

        Orders order = ordersRepository.save(orders);
        OrdersDto ordersDto = new OrdersDto(order);

        if (type == 1) {

            List<Orders> setCompra = ordersRepository.match();
            List<Orders> setVenda = ordersRepository.match2();
            if (setCompra != null) {

                if (true) {
                    for (Orders compraOrd : setCompra) {
                        ordersRepository.atualizaDolarCompra(compraOrd.getIdUser(), compraOrd);
                        ordersRepository.atualizaVolumeCompra(compraOrd, compraOrd.getIdUser());
                        ordersRepository.atualizaBalanceCompra(compraOrd.getId(), compraOrd.getIdUser(),
                                compraOrd.getIdStock());
                    }

                }
                if (!setVenda.isEmpty()) {
                    for (Orders vendamatchOrders : setVenda) {

                        ordersRepository.atualizaBalanceVenda(vendamatchOrders.getId(),
                                vendamatchOrders.getIdUser(),
                                vendamatchOrders.getIdStock());
                        ordersRepository.atualizaDolarVenda(vendamatchOrders.getIdUser(), vendamatchOrders);
                        ordersRepository.atualizaVolumeVenda(vendamatchOrders, vendamatchOrders.getIdUser());

                    }
                }
                ordersRepository.fecharStatusCompra();

            }

        }
        return ordersDto;

    }

    public OrdersDto venda(OrdersDto orderVenda) {

        int type = orderVenda.getType();

        System.out.println("Ordem de Venda criada com sucesso!");

        if (type == 2) {
            List<Orders> setVenda = ordersRepository.match();
            List<Orders> setCompra = ordersRepository.match2();

            if (true) {
                if (!setVenda.isEmpty()) {
                    List<Orders> venda = ordersRepository.match();
                    System.out.println(venda);
                    for (Orders vendaOrder : setVenda) {

                        ordersRepository.atualizaVolumeVenda(vendaOrder, vendaOrder.getIdUser());
                        System.out.println();
                        ordersRepository.atualizaDolarVenda(vendaOrder.getIdUser(), vendaOrder);
                        ordersRepository.atualizaBalanceVenda(vendaOrder.getId(), vendaOrder.getIdUser(),
                                vendaOrder.getIdStock());

                    }

                }
                if (!setCompra.isEmpty()) {
                    for (Orders compraOrder : setCompra) {
                        ordersRepository.atualizaBalanceCompra(compraOrder.getId(), compraOrder.getIdUser(),
                                compraOrder.getIdStock());
                        ordersRepository.atualizaDolarCompra(compraOrder.getIdUser(), compraOrder);
                        System.out.println(compraOrder);
                        ordersRepository.atualizaVolumeCompra(compraOrder, compraOrder.getIdUser());

                    }

                }
                ordersRepository.fecharStatusVenda();
            }

        }
        return null;
    }
}
