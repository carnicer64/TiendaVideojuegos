package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Client;
import com.svalero.TiendaVideojuegos.domain.dto.ClientInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.ClientOutDTO;
import com.svalero.TiendaVideojuegos.exception.ClientNotFoundException;

import java.util.List;

public interface ClientService {

    List<ClientOutDTO> findAll();

    Client findById(long idClient) throws ClientNotFoundException;

    Client addClient(ClientInDTO clientInDTO);

    void deleteClient(long idClient) throws ClientNotFoundException;

    Client modifyClient(long id, ClientInDTO clientInDTO) throws ClientNotFoundException;
}
