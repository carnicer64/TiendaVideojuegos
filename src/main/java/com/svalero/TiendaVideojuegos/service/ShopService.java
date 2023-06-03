package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Shop;
import com.svalero.TiendaVideojuegos.domain.dto.ShopInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.ShopOutDTO;
import com.svalero.TiendaVideojuegos.exception.EmployeeNotFoundException;
import com.svalero.TiendaVideojuegos.exception.ShopNotFoundException;

import java.util.List;

public interface ShopService {
    List<ShopOutDTO> findAll();

    List<ShopOutDTO> findByName(String name);

    List<ShopOutDTO> findByAdress(String adress);

    List<ShopOutDTO> findByTlf(String s);

    ShopOutDTO findById(long id) throws ShopNotFoundException;

    Shop findByEmployee(long id);


    Shop addShop(ShopInDTO shopInDTO) throws EmployeeNotFoundException;

    void deleteShop(long id) throws ShopNotFoundException;

    Shop modifyShop(long id, Shop shop) throws ShopNotFoundException;

}
