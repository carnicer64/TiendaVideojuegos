package com.svalero.TiendaVideojuegos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank(message = "La cantidad no puede estar vacia")
    @NotNull(message = "La cantidad es obligatoria")
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
