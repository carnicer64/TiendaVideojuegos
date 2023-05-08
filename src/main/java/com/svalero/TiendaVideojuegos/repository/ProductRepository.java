package com.svalero.TiendaVideojuegos.repository;

import com.svalero.TiendaVideojuegos.domain.Product;
import com.svalero.TiendaVideojuegos.domain.dto.ProductOutDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findAllByName(String name);

    List<Product> findAllByCost(String cost);

    List<Product> findAllBySale(String sale);


}
