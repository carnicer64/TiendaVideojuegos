package com.svalero.TiendaVideojuegos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInDTO {

    private String name;
    private String email;
    private Boolean boss;





}
