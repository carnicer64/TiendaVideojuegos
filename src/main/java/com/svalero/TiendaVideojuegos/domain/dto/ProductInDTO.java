package com.svalero.TiendaVideojuegos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInDTO {

    private long id;
    private String name;
    private double cost;
    private double sale;
    private int barCode;
    private String imageURL;
}
