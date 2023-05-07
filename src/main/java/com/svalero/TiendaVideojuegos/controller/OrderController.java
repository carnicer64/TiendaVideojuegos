package com.svalero.TiendaVideojuegos.controller;

import com.svalero.TiendaVideojuegos.Util.ErrorMessage;
import com.svalero.TiendaVideojuegos.domain.Order;
import com.svalero.TiendaVideojuegos.domain.dto.OrderInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.OrderOutDTO;
import com.svalero.TiendaVideojuegos.exception.ClientNotFoundException;
import com.svalero.TiendaVideojuegos.exception.OrderNotFoundException;
import com.svalero.TiendaVideojuegos.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/orders")
    public ResponseEntity<List<OrderOutDTO>> getOrders(){

        logger.info("GETTING Clients");

        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable long id) throws OrderNotFoundException {

        Order order = orderService.findById(id);

        return ResponseEntity.ok(order);
    }

    @GetMapping ("/clients/{id}/orders")
    public ResponseEntity<List<OrderOutDTO>> getOrdersByClient(@PathVariable long id) {

        List<OrderOutDTO> orders = orderService.findByClient(id);

        return ResponseEntity.ok(orders);
    }

    @PostMapping("/clients/{id}")
    public ResponseEntity<Order> addOrder(@PathVariable long id, @RequestBody OrderInDTO orderInDTO) throws ClientNotFoundException {

        Order order = orderService.addBus(id, orderInDTO);

        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) throws OrderNotFoundException {

        orderService.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> modifyBus(@PathVariable long id, @RequestBody Order order) throws OrderNotFoundException {

        Order modOrder = orderService.modifyOrder(id, order);

        return ResponseEntity.status(HttpStatus.OK).body(modOrder);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleOrderNotFoundException(OrderNotFoundException onfe) {
        logger.error(onfe.getMessage(), onfe);
        ErrorMessage errorMessage = new ErrorMessage(404, onfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleClientNotFoundException(ClientNotFoundException cnfe) {
        ErrorMessage errorMessage = new ErrorMessage(404, cnfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
