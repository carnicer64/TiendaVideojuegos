package com.svalero.TiendaVideojuegos.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private double cost; //Precio de coste
    @Column
    private double sale; //Precio de venta
    @Column
    private int barCode;
    @Column
    private String imageURL;
}
