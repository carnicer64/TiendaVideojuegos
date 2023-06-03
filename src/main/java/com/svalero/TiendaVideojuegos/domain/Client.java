package com.svalero.TiendaVideojuegos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//He tenido que poner CLIENTES porque CLIENT y CLIENTS son palabras reservadas de SQL

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "clientes")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String adress;
    @Column
    private String email;
    @Column
    private String tlf;
    @Column
    private String nif;

}
