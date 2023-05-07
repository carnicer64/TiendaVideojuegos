package com.svalero.TiendaVideojuegos.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column
    private LocalTime pDate;
    @Column
    private LocalTime dDate;
    @Column
    private double oPrice;
    @Column
    private String oNote;


    @OneToOne
    @JoinColumn(name = "clientes_id")
    private Client client;




}
