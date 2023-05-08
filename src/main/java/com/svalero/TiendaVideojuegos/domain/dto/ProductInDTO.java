package com.svalero.TiendaVideojuegos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInDTO {

    private long id;
    private String pName;
    private double costPrice;
    private double salePrice;
    private int barCode;
    private String imageURL;
}
