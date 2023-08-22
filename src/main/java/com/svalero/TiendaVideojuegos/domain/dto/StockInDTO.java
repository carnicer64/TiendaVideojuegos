package com.svalero.TiendaVideojuegos.domain.dto;

import com.svalero.TiendaVideojuegos.domain.Product;
import com.svalero.TiendaVideojuegos.domain.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockInDTO {

    private int amount;
    private String note;
    private Product product;
    private Shop shop;


}