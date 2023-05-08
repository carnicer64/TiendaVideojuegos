package com.svalero.TiendaVideojuegos.exception;

public class OrderLineNotFoundException extends Exception{

    public OrderLineNotFoundException() {
        super("OrderLine not found");
    }

    public OrderLineNotFoundException(String message) {
        super(message);
    }
}
