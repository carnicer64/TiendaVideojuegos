package com.svalero.TiendaVideojuegos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int amount;
    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "shops_id")
    private Shop shop;


}
