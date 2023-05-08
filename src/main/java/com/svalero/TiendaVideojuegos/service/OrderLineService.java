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

    OrderLine findById(long id) throws OrderLineNotFoundException;

    List<OrderLine> findByStock(long id);

    List<OrderLine> findByOrder(long id);

    OrderLine addOrderLine(OrderLineInDTO orderLineInDTO) throws StockNotFoundException, OrderNotFoundException;

    void deleteOrderLine(long id) throws OrderLineNotFoundException;

    OrderLine modifyOrderLine(long id, OrderLine orderLine) throws OrderLineNotFoundException;
}
