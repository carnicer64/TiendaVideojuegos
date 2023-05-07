package com.svalero.TiendaVideojuegos.service;


import com.svalero.TiendaVideojuegos.domain.Client;
import com.svalero.TiendaVideojuegos.domain.dto.ClientInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.ClientOutDTO;
import com.svalero.TiendaVideojuegos.exception.ClientNotFoundException;
import com.svalero.TiendaVideojuegos.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);


    @Override
    public List<ClientOutDTO> findAll() {
        logger.info("Finding Client");
        List<Client> clients = clientRepository.findAll();
        List<ClientOutDTO> clientOutDTO = modelMapper.map(clients, new TypeToken<List<ClientOutDTO>>() {}.getType());
        logger.info("Client finded");
        return clientOutDTO;
    }

    @Override
    public Client findById(long idClient) throws ClientNotFoundException {
        logger.info("Finding Client by id");
        return clientRepository.findById(idClient)
                .orElseThrow(ClientNotFoundException::new);
    }


    //PUEDE DAR PROBLEMAS EL ID EN EL clientINdto
    @Override
    public Client addClient(ClientInDTO clientInDTO)  {
        logger.info("Adding Client");
        Client newClient = new Client();
        modelMapper.map(clientInDTO, newClient);
        logger.info("The client was added");

        return clientRepository.save(newClient);
    }

    @Override
    public Client modifyClient(long idClient, ClientInDTO clientInDTO) throws ClientNotFoundException {
        logger.info("Modifying Client");
        Client modClient = clientRepository.findById(idClient).orElseThrow(ClientNotFoundException::new);
        modelMapper.map(clientInDTO, modClient);
        logger.info("The client was modified");
        return clientRepository.save(modClient);
    }

    @Override
    public void deleteClient(long idClient) throws ClientNotFoundException {
        logger.info("The client is going to be deleted");
        Client delClient = clientRepository.findById(idClient).orElseThrow(ClientNotFoundException::new);
        clientRepository.delete(delClient);
        logger.info("The client was deleted");

    }
}
