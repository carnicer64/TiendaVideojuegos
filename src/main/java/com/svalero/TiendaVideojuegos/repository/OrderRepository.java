package com.svalero.TiendaVideojuegos.repository;

import com.svalero.TiendaVideojuegos.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAll();


    List<Order> findByClient_Id(long id);
}
