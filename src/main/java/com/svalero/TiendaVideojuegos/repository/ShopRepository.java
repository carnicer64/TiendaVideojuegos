package com.svalero.TiendaVideojuegos.repository;

import com.svalero.TiendaVideojuegos.domain.Order;
import com.svalero.TiendaVideojuegos.domain.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopRepository extends CrudRepository<Shop, Long> {

    List<Shop> findAll();

    List<Shop> findByName(String name);

    List<Shop> findByAdress(String adress);

    Shop findByEmployee_Id(long id);

    List<Shop> findByTlf(String tlf);
}
