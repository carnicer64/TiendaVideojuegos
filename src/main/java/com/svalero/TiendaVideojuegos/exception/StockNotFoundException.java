package com.svalero.TiendaVideojuegos.exception;

public class StockNotFoundException extends Exception {

    public StockNotFoundException() {
        super("Stock not found");
    }

    public StockNotFoundException(String message) {
        super(message);
    }
}
