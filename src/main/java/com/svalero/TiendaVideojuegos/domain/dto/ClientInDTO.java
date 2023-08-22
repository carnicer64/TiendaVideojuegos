package com.svalero.TiendaVideojuegos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInDTO {
    private String name;
    private String adress;
    private String email;
    private String tlf;
    private String nif;
}
