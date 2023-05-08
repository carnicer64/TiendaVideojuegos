package com.svalero.TiendaVideojuegos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orderlines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String amount;
    @Column
    private String price;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "stocks_id")
    private Stock stock;
}
