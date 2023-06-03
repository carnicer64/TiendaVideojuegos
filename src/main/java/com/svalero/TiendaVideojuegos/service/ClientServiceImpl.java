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
    public ClientOutDTO findById(long idClient) throws ClientNotFoundException {
        logger.info("Finding Client by id");

        Client client = clientRepository.findById(idClient).orElseThrow(ClientNotFoundException::new);
        ClientOutDTO clientOutDTO = modelMapper.map(client, new TypeToken<ClientOutDTO>() {}.getType());

        return clientOutDTO;
    }

    @Override
    public List<ClientOutDTO> findByName(String name) {
        logger.info("Finding Client by name");
        logger.info("Name: " + name);

        List<Client> clients = clientRepository.findByName(name);
        List<ClientOutDTO> clientOutDTO = modelMapper.map(clients, new TypeToken<List<ClientOutDTO>>() {}.getType());

        return clientOutDTO;
    }

    //PUEDE DAR PROBLEMAS EL ID EN EL clientINdto
    @Override
    public Client addClient(ClientInDTO clientInDTO)  {
        logger.info("Adding Client");
        Client newClient = new Client();

        logger.info("Client " + clientInDTO);
        modelMapper.map(clientInDTO, newClient);

        logger.info("The client was added");
        logger.info("Client " + newClient);
        return clientRepository.save(newClient);
    }

    @Override
    public Client modifyClient(long id, ClientInDTO clientInDTO) throws ClientNotFoundException {
        logger.info("Modifying Client");

        Client modClient = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        modelMapper.map(clientInDTO, modClient);
        modClient.setId(id);


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
