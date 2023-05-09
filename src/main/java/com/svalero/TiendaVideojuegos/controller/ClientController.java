package com.svalero.TiendaVideojuegos.controller;

import com.svalero.TiendaVideojuegos.Util.ErrorMessage;
import com.svalero.TiendaVideojuegos.domain.Client;
import com.svalero.TiendaVideojuegos.domain.dto.ClientInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.ClientOutDTO;
import com.svalero.TiendaVideojuegos.domain.dto.ProductOutDTO;
import com.svalero.TiendaVideojuegos.exception.ClientNotFoundException;
import com.svalero.TiendaVideojuegos.exception.ProductNotFoundException;
import com.svalero.TiendaVideojuegos.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("/clients")
    public ResponseEntity<List<ClientOutDTO>> getClients(@RequestParam Map<String, String> data) throws ClientNotFoundException {
        logger.info("GET Client");

        if (data.isEmpty()) {
            logger.info("Showing all clients");
            return ResponseEntity.ok(clientService.findAll());
        } else if(data.containsKey("id")) {
            logger.info("id: " + data.get("id"));
            List<ClientOutDTO> client = new ArrayList<>();
            client.add(clientService.findById(Long.parseLong(data.get(("id")))));
            logger.info("Showing all clients by ID");
            return ResponseEntity.ok(client);
        } else if(data.containsKey("name")) {
            logger.info("Name: " + data.get("name"));
            List<ClientOutDTO> client = clientService.findByName(data.get(("name")));
            logger.info("Showing all products by name");
            return ResponseEntity.ok(client);
        }
        logger.info("Bad Request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




    /*@GetMapping("/clients")
    public ResponseEntity<List<ClientOutDTO>> getClients() throws ClientNotFoundException{
        logger.info("Begin GET Clients");
        logger.info("End GET Clients");
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/clients/{idClient}")
    public ResponseEntity<Client> getClient(@PathVariable long idClient) throws ClientNotFoundException {
        logger.info("Begin GET Client");
        Client client = clientService.findById(idClient);
        logger.info("End GET Client");
        return ResponseEntity.ok(client);
    }*/

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@RequestBody ClientInDTO clientInDTO) {
        logger.info("Begin POST Client");
        logger.info("Client " + clientInDTO);


        Client client = clientService.addClient(clientInDTO);
        logger.info("End POST Client");
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> modifyClient(@PathVariable long id, @Valid @RequestBody ClientInDTO clientInDTO) throws ClientNotFoundException{
        logger.info("Begin PUT Client");

        Client modClient = clientService.modifyClient(id, clientInDTO);
        modClient.setId(id);

        logger.info("End PUT Client");
        return ResponseEntity.status(HttpStatus.OK).body(modClient);
    }

    @DeleteMapping("/clients/{idClient}")
    public ResponseEntity<Void> deleteClient(@PathVariable long idClient) throws ClientNotFoundException {
        logger.info("Begin DELETE Client");
        clientService.deleteClient(idClient);
        logger. info("End DELETE Client");
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleClientNotFoundException(ClientNotFoundException cnfe) {
        logger.error(cnfe.getMessage(), cnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, cnfe.getMessage());
        logger.info("Client not found");
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
