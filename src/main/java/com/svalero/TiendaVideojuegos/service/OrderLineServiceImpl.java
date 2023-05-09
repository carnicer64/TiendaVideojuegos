package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.*;
import com.svalero.TiendaVideojuegos.domain.dto.OrderLineInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.OrderLineOutDTO;
import com.svalero.TiendaVideojuegos.domain.dto.StockOutDTO;
import com.svalero.TiendaVideojuegos.exception.*;
import com.svalero.TiendaVideojuegos.repository.OrderLineRepository;
import com.svalero.TiendaVideojuegos.repository.OrderRepository;
import com.svalero.TiendaVideojuegos.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService{

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(OrderLineServiceImpl.class);

    @Override
    public List<OrderLineOutDTO> findAll() {
        List<OrderLine> oLines = orderLineRepository.findAll();
        List<OrderLineOutDTO> orderLineOutDTO = modelMapper.map(oLines, new TypeToken<List<OrderLineOutDTO>>() {}.getType());

        return orderLineOutDTO;
    }

    @Override
    public List<OrderLineOutDTO> findByAmount(String amount) {
        logger.info("Amount: " + amount);
        List<OrderLine> oLines = orderLineRepository.findByAmount(amount);
        List<OrderLineOutDTO> OrderLineOutDTO = modelMapper.map(oLines, new TypeToken<List<OrderLineOutDTO>>() {}.getType());
        return OrderLineOutDTO;
    }

    @Override
    public List<OrderLineOutDTO> findByName(String name) {
        logger.info("Name: " + name);
        List<OrderLine> oLines = orderLineRepository.findByName(name);
        List<OrderLineOutDTO> OrderLineOutDTO = modelMapper.map(oLines, new TypeToken<List<OrderLineOutDTO>>() {}.getType());
        return OrderLineOutDTO;
    }

    @Override
    public List<OrderLineOutDTO> findByPrice(String price) {
        logger.info("Line price: " + price);
        List<OrderLine> oLines = orderLineRepository.findByPrice(price);
        List<OrderLineOutDTO> OrderLineOutDTO = modelMapper.map(oLines, new TypeToken<List<OrderLineOutDTO>>() {}.getType());
        return OrderLineOutDTO;
    }

    @Override
    public OrderLineOutDTO findById(long id) throws OrderLineNotFoundException {
        logger.info("ID: " + id);

        OrderLine orderLine = orderLineRepository.findById(id).orElseThrow(OrderLineNotFoundException::new);
        OrderLineOutDTO orderLineOutDTO = modelMapper.map(orderLine, new TypeToken<OrderLineOutDTO>() {}.getType());

        return orderLineOutDTO;
    }

    @Override
    public OrderLineOutDTO findByStock(long id) {
        logger.info("IDStock: " + id);

        OrderLine orderLine = orderLineRepository.findByStock_Id(id);
        OrderLineOutDTO orderLineOutDTO = modelMapper.map(orderLine, new TypeToken<OrderLineOutDTO>() {}.getType());

        return orderLineOutDTO;
    }

    @Override
    public OrderLineOutDTO findByOrder(long id) {
        logger.info("IDOrder: " + id);

        OrderLine orderLine = orderLineRepository.findByOrder_Id(id);
        OrderLineOutDTO orderLineOutDTO = modelMapper.map(orderLine, new TypeToken<OrderLineOutDTO>() {}.getType());

        return orderLineOutDTO;
    }

    @Override
    public OrderLine addOrderLine(OrderLineInDTO orderLineInDTO) throws StockNotFoundException, OrderNotFoundException {
        logger.info("Creating shop");

        OrderLine newOrderLine = new OrderLine();
        modelMapper.map(orderLineInDTO, newOrderLine);

        Order order = orderRepository.findById(orderLineInDTO.getOrder().getId()).orElseThrow(OrderNotFoundException::new);
        Stock stock = stockRepository.findById(orderLineInDTO.getStock().getId()).orElseThrow(StockNotFoundException::new);

        newOrderLine.setOrder(order);
        newOrderLine.setStock(stock);

        return orderLineRepository.save(newOrderLine);
    }

    @Override
    public void deleteOrderLine(long id) throws OrderLineNotFoundException{
        OrderLine orderLine = orderLineRepository.findById(id).orElseThrow(OrderLineNotFoundException::new);

        orderLineRepository.delete(orderLine);
    }

    @Override
    public OrderLine modifyOrderLine(long id, OrderLine orderLine) throws OrderLineNotFoundException {
        logger.info("Modifying stock");

        OrderLine existingOrderLine = orderLineRepository.findById(id).orElseThrow(OrderLineNotFoundException::new);

        modelMapper.map(orderLine, existingOrderLine);
        existingOrderLine.setId(id);

        return orderLineRepository.save(existingOrderLine);
    }
}
