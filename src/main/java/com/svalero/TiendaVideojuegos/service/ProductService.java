package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Product;
import com.svalero.TiendaVideojuegos.domain.dto.ProductInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.ProductOutDTO;
import com.svalero.TiendaVideojuegos.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    List<ProductOutDTO> findAll();

    List<ProductOutDTO> findByName(String name);

    List<ProductOutDTO> findByCost(String cost);

    List<ProductOutDTO> findBySale(String sale);

    Product findById(long id) throws ProductNotFoundException;

    Product addProduct(ProductInDTO productInDTO);

    void deleteProduct(long id) throws ProductNotFoundException;

    Product modifyProduct(long id, Product product) throws ProductNotFoundException;
}
