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
    private String cName;
    @Column
    private String cAdress;
    @Column
    private String cEmail;
    @Column
    private String cTlf;
    @Column
    private String cNIF;

}
