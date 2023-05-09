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

    List<StockOutDTO> findByNote(String note);

    List<StockOutDTO> findByAmount(int amount);

    StockOutDTO findById(long id) throws StockNotFoundException;

    List<StockOutDTO> findByProduct(long id);

    List<StockOutDTO> findByShop(long id);

    Stock addStock(StockInDTO stockInDTO) throws ProductNotFoundException, ShopNotFoundException;

    void deleteStock(long id) throws StockNotFoundException;

    Stock modifyStock(long id, Stock stock) throws StockNotFoundException;
}
