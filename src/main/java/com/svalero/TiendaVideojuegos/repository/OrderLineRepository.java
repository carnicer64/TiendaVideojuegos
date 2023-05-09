package com.svalero.TiendaVideojuegos.repository;

import com.svalero.TiendaVideojuegos.domain.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends CrudRepository<OrderLine, Long> {
    
    List<OrderLine> findAll();

    OrderLine findByStock_Id(long id);

    OrderLine findByOrder_Id(long id);

    List<OrderLine> findByAmount(String amount);

    List<OrderLine> findByPrice(String price);

    List<OrderLine> findByName(String name);
}
