package com.svalero.TiendaVideojuegos.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.svalero.TiendaVideojuegos.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderOutDTO {

    private long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate pDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dDate;
    private double orderPrice;
    private String note;
    private Client client;

}
