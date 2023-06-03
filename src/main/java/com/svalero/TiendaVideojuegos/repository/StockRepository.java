package com.svalero.TiendaVideojuegos.repository;

import com.svalero.TiendaVideojuegos.domain.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {

    List<Stock>  findAll();

    List<Stock> findByNote(String note);

    List<Stock> findByProduct_Id(long id);

    List<Stock> findByShop_Id(long id);

    List<Stock> findByAmount(int amount);
}
