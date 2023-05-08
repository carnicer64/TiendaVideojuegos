package com.svalero.TiendaVideojuegos.exception;

public class ShopNotFoundException extends Exception{

    public ShopNotFoundException() {
        super("Shop not found");
    }

    public ShopNotFoundException(String message) {
        super(message);
    }
}
