package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.controller.OrderController;
import com.svalero.TiendaVideojuegos.domain.Client;
import com.svalero.TiendaVideojuegos.domain.Employee;
import com.svalero.TiendaVideojuegos.domain.Order;
import com.svalero.TiendaVideojuegos.domain.dto.EmployeeOutDTO;
import com.svalero.TiendaVideojuegos.domain.dto.OrderInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.OrderOutDTO;
import com.svalero.TiendaVideojuegos.exception.ClientNotFoundException;
import com.svalero.TiendaVideojuegos.exception.EmployeeNotFoundException;
import com.svalero.TiendaVideojuegos.exception.OrderNotFoundException;
import com.svalero.TiendaVideojuegos.repository.ClientRepository;
import com.svalero.TiendaVideojuegos.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public List<OrderOutDTO> findAll() {
        logger.info("Finding All orders");

        List<Order> orders =orderRepository.findAll();
        logger.info("Orders: " + orders);
        List<OrderOutDTO> orderOutDTO = modelMapper.map(orders, new TypeToken<List<OrderOutDTO>>() {}.getType());

        return orderOutDTO;
    }

    @Override
    public OrderOutDTO findById(long id) throws OrderNotFoundException {
        logger.info("ID: " + id);

        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        OrderOutDTO orderOutDTO = modelMapper.map(order, new TypeToken<OrderOutDTO>() {}.getType());

        return orderOutDTO;
    }

    @Override
    public List<OrderOutDTO> findByClient(long id) {
        logger.info("IDClient: " + id);

        List<Order> orders = orderRepository.findByClient_Id(id);
        List<OrderOutDTO> orderOutDTO = modelMapper.map(orders, new TypeToken<List<OrderOutDTO>>() {}.getType());
        return orderOutDTO;
    }

    @Override
    public Order addOrder(OrderInDTO orderInDTO) throws ClientNotFoundException {
        logger.info("Creating Order");
        logger.info("order " + orderInDTO);

        Order newOrder = new Order();
        modelMapper.map(orderInDTO, newOrder);

        logger.info("Order " + newOrder);
        Client client = clientRepository.findById(newOrder.getClient().getId()).orElseThrow(ClientNotFoundException::new);
        newOrder.setClient(client);

        return orderRepository.save(newOrder);
    }

    @Override
    public void deleteOrder(long id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        orderRepository.delete(order);

    }

    @Override
    public Order modifyOrder(long id, Order order) throws OrderNotFoundException {
        logger.info("Modifying order");

        Order existingOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        modelMapper.map(order, existingOrder);
        existingOrder.setId(id);

        return orderRepository.save(existingOrder);
    }

}
