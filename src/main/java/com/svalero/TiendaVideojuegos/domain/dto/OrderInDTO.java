package com.svalero.TiendaVideojuegos.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.svalero.TiendaVideojuegos.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInDTO {

    private long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date pDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dDate;
    private double orderPrice;
    private String note;
    private Client client;

}
