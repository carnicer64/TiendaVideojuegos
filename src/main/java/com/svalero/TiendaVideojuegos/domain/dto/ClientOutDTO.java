package com.svalero.TiendaVideojuegos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientOutDTO {

    private long idClient;
    private String cName;
    private String cAdress;
    private String cEmail;
    private String cTlf;
    private String cNIF;
}
