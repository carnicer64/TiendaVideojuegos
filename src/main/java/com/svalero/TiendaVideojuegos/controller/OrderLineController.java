package com.svalero.TiendaVideojuegos.controller;

import com.svalero.TiendaVideojuegos.Util.ErrorMessage;
import com.svalero.TiendaVideojuegos.domain.OrderLine;
import com.svalero.TiendaVideojuegos.domain.dto.OrderLineInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.OrderLineOutDTO;
import com.svalero.TiendaVideojuegos.exception.*;
import com.svalero.TiendaVideojuegos.service.OrderLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    private final Logger logger = LoggerFactory.getLogger(OrderLineController.class);


    @GetMapping("/orderlines")
    public ResponseEntity<List<OrderLineOutDTO>> getOrderLines(@RequestParam Map<String, String> data) throws OrderLineNotFoundException{
        logger.info("GET OrderLines");

        if (data.isEmpty()) {
            logger.info("Showing all order lines");
            return ResponseEntity.ok(orderLineService.findAll());
        } else if(data.containsKey("id")) {
            logger.info("id: " + data.get("id"));
            List<OrderLineOutDTO> orderLine = new ArrayList<>();
            orderLine.add(orderLineService.findById(Long.parseLong(data.get(("id")))));
            logger.info("Showing all order lines by ID");
            return ResponseEntity.ok(orderLine);
        } else if(data.containsKey("idStock")) {
            logger.info("idStock: " + data.get("idStock"));
            List<OrderLineOutDTO> orderLine = new ArrayList<>();
            orderLine.add(orderLineService.findByStock(Long.parseLong(data.get(("idStock")))));
            logger.info("Showing all order lines by stock ID");
            return ResponseEntity.ok(orderLine);
        } else if(data.containsKey("idOrder")) {
            logger.info("idOrder: " + data.get("idOrder"));
            List<OrderLineOutDTO> orderLine = new ArrayList<>();
            orderLine.add(orderLineService.findByOrder(Long.parseLong(data.get(("idOrder")))));
            logger.info("Showing all order lines by order ID");
            return ResponseEntity.ok(orderLine);
        }else if(data.containsKey("amount")) {
            logger.info("Amount: " + data.get("amount"));
            List<OrderLineOutDTO> oLines = orderLineService.findByAmount(data.get(("amount")));
            logger.info("Showing all order lines by amount");
            return ResponseEntity.ok(oLines);
        } else if(data.containsKey("name")) {
            logger.info("Product name: " + data.get("name"));
            List<OrderLineOutDTO> oLines = orderLineService.findByName(data.get(("name")));
            logger.info("Showing all order lines by name");
            return ResponseEntity.ok(oLines);
        } else if(data.containsKey("price")) {
            logger.info("Line Price: " + data.get("price"));
            List<OrderLineOutDTO> oLines = orderLineService.findByPrice(data.get(("price")));
            logger.info("Showing all order lines by price");
            return ResponseEntity.ok(oLines);
        }
        logger.info("Bad Request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*@GetMapping("/orderlines/{id}")
    public ResponseEntity<OrderLine> getOrderLine(@PathVariable long id) throws OrderLineNotFoundException {
        logger.info("Showing all order lines by ID");
        OrderLine orderLine = orderLineService.findById(id);
        logger.info("GET END");
        return ResponseEntity.ok(orderLine);
    }

    @GetMapping ("/stocks/{id}/orderlines")
    public ResponseEntity<List<OrderLine>> getOrderLineByStock(@PathVariable long id){
        logger.info("GET order lines by stock id");
        List<OrderLine> oLines = orderLineService.findByStock(id);
        logger.info("GET END");
        return ResponseEntity.ok(oLines);
    }

    @GetMapping ("/orders/{id}/orderlines")
    public ResponseEntity<List<OrderLine>> getOrderLineByOrder(@PathVariable long id){
        logger.info("GET order lines by order id");
        List<OrderLine> oLines = orderLineService.findByOrder(id);
        logger.info("GET END");
        return ResponseEntity.ok(oLines);
    }*/

    @PostMapping("/orderlines")
    public ResponseEntity<OrderLine> addOrderLine(@RequestBody OrderLineInDTO orderLineInDTO) throws StockNotFoundException, OrderNotFoundException {
        logger.info("POST Adding Order Line");
        OrderLine orderLine = orderLineService.addOrderLine(orderLineInDTO);
        logger.info("POST END");
        return ResponseEntity.status(HttpStatus.OK).body(orderLine);
    }

    @DeleteMapping("/orderlines/{id}")
    public ResponseEntity<Void> deleteOrderLines(@PathVariable long id) throws OrderLineNotFoundException {
        logger.info("DELETE Order Line");
        orderLineService.deleteOrderLine(id);
        logger.info("DELETE END");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/orderlines/{id}")
    public ResponseEntity<OrderLine> modifyStock(@PathVariable long id, @RequestBody OrderLine orderLine) throws OrderLineNotFoundException {
        logger.info("PUT MODIFYING Order Line");

        OrderLine modOrderLine = orderLineService.modifyOrderLine(id, orderLine);
        logger.info("PUT END");

        return ResponseEntity.status(HttpStatus.OK).body(modOrderLine);
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleStockNotFoundException(StockNotFoundException stnfe) {
        logger.error(stnfe.getMessage(), stnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, stnfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleOrderNotFoundException(OrderNotFoundException onfe) {
        logger.error(onfe.getMessage(), onfe);
        ErrorMessage errorMessage = new ErrorMessage(404, onfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderLineNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleOrderLineNotFoundException(OrderNotFoundException olnfe) {
        logger.error(olnfe.getMessage(), olnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, olnfe.getMessage());
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
