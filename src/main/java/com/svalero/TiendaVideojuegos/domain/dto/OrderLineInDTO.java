package com.svalero.TiendaVideojuegos.domain.dto;

import com.svalero.TiendaVideojuegos.domain.Order;
import com.svalero.TiendaVideojuegos.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineInDTO {

    private String name;
    private String amount;
    private String price;
    private Order order;
    private Stock stock;
}