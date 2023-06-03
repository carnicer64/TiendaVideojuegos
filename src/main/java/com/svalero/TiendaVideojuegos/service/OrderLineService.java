package com.svalero.TiendaVideojuegos.service;


import com.svalero.TiendaVideojuegos.domain.OrderLine;
import com.svalero.TiendaVideojuegos.domain.dto.OrderLineInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.OrderLineOutDTO;
import com.svalero.TiendaVideojuegos.exception.OrderLineNotFoundException;
import com.svalero.TiendaVideojuegos.exception.OrderNotFoundException;
import com.svalero.TiendaVideojuegos.exception.StockNotFoundException;

import java.util.List;

public interface OrderLineService {
    List<OrderLineOutDTO> findAll();

    List<OrderLineOutDTO> findByAmount(String s);

    List<OrderLineOutDTO> findByName(String s);

    List<OrderLineOutDTO> findByPrice(String s);

    OrderLineOutDTO findById(long id) throws OrderLineNotFoundException;

    OrderLineOutDTO findByStock(long id);

    OrderLineOutDTO findByOrder(long id);

    OrderLine addOrderLine(OrderLineInDTO orderLineInDTO) throws StockNotFoundException, OrderNotFoundException;

    void deleteOrderLine(long id) throws OrderLineNotFoundException;

    OrderLine modifyOrderLine(long id, OrderLine orderLine) throws OrderLineNotFoundException;
}
