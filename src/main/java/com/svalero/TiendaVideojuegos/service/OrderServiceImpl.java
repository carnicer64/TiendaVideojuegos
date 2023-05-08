package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Client;
import com.svalero.TiendaVideojuegos.domain.Order;
import com.svalero.TiendaVideojuegos.domain.dto.OrderInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.OrderOutDTO;
import com.svalero.TiendaVideojuegos.exception.ClientNotFoundException;
import com.svalero.TiendaVideojuegos.exception.OrderNotFoundException;
import com.svalero.TiendaVideojuegos.repository.ClientRepository;
import com.svalero.TiendaVideojuegos.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    @Override
    public List<OrderOutDTO> findAll() {
        List<Order> orders =orderRepository.findAll();
        List<OrderOutDTO> orderOutDTO = modelMapper.map(orders, new TypeToken<List<OrderOutDTO>>() {}.getType());

        return orderOutDTO;
    }

    @Override
    public Order findById(long id) throws OrderNotFoundException {

        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public List<OrderOutDTO> findByClient(long id) {
        List<Order> orders = orderRepository.findByClient_Id(id);
        List<OrderOutDTO> orderOutDTO = modelMapper.map(orders, new TypeToken<List<OrderOutDTO>>() {}.getType());
        return orderOutDTO;
    }

    @Override
    public Order addOrder(long id, OrderInDTO orderInDTO) throws ClientNotFoundException {

        Order newOrder = new Order();
        modelMapper.map(orderInDTO, newOrder);

        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
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
        Order existingOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

        modelMapper.map(order, existingOrder);
        existingOrder.setId(id);

        return orderRepository.save(existingOrder);
    }

}
