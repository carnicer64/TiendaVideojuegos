package com.svalero.TiendaVideojuegos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employees")
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank(message = "El nombre no puede estar vacio")
    @NotNull(message = "El nombre es obligatorio")
    private String name;
    @Column
    private String email;
    @Column

    private Boolean boss;





}
