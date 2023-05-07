package com.svalero.TiendaVideojuegos.exception;

public class ClientNotFoundException extends Exception{

    public ClientNotFoundException() {
        super("Client not found");
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}
