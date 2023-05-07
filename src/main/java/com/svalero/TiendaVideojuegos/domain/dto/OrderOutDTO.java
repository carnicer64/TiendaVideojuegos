package com.svalero.TiendaVideojuegos.domain.dto;

import com.svalero.TiendaVideojuegos.domain.Client;

import java.time.LocalTime;

public class OrderOutDTO {

    private long idOrder;
    private LocalTime pDate;
    private LocalTime dDate;
    private double oPrice;
    private String oNote;
    private Client client;

}
