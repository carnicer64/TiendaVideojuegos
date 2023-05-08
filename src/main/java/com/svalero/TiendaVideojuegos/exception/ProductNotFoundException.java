package com.svalero.TiendaVideojuegos.exception;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException() {
        super("Order not found");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
