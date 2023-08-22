package com.svalero.TiendaVideojuegos.domain.dto;

import com.svalero.TiendaVideojuegos.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopInDTO {

    private String name;
    private String adress;
    private String tlf;
    private String latitude;
    private String longitude;
    private Employee employee;
}
