package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Stock;
import com.svalero.TiendaVideojuegos.domain.dto.ShopInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.StockInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.StockOutDTO;
import com.svalero.TiendaVideojuegos.exception.ProductNotFoundException;
import com.svalero.TiendaVideojuegos.exception.ShopNotFoundException;
import com.svalero.TiendaVideojuegos.exception.StockNotFoundException;

import java.util.List;

public interface StockService {
    List<StockOutDTO> findAll();

    List<StockOutDTO> findByNote(String s);

    List<StockOutDTO> findByAmount(String s);

    Stock findById(long id) throws StockNotFoundException;

    List<Stock> findByProduct(long id);

    Stock findByShop(long id);

    Stock addStock(StockInDTO stockInDTO) throws ProductNotFoundException, ShopNotFoundException;

    void deleteStock(long id) throws StockNotFoundException;

    Stock modifyStock(long id, Stock stock) throws StockNotFoundException;
}
