package com.svalero.TiendaVideojuegos.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "shops")
public class Shop{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String adress;
    @Column
    private String tlf;
    @Column
    private String latitude;
    @Column
    private String longitude;

    @OneToOne
    @JoinColumn(name = "employees_id")
    private Employee employee; // id del Gerente

}
