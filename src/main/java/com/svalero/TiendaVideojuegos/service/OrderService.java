package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Client;
import com.svalero.TiendaVideojuegos.domain.Order;
import com.svalero.TiendaVideojuegos.domain.dto.OrderInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.OrderOutDTO;
import com.svalero.TiendaVideojuegos.exception.ClientNotFoundException;
import com.svalero.TiendaVideojuegos.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    List<OrderOutDTO> findAll();

    OrderOutDTO findById(long id) throws OrderNotFoundException;

    List<OrderOutDTO> findByClient(long id);

    Order addOrder(OrderInDTO orderInDTO) throws ClientNotFoundException;

    void deleteOrder(long id) throws OrderNotFoundException;

    Order modifyOrder(long id, Order order) throws OrderNotFoundException;
}
