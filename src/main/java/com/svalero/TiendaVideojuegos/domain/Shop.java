package com.svalero.TiendaVideojuegos.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "La direccion no puede estar vacia")
    @NotNull(message = "La direccion es obligatoria")
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
