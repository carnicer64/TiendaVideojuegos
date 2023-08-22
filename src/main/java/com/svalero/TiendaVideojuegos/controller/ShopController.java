package com.svalero.TiendaVideojuegos.controller;

import com.svalero.TiendaVideojuegos.Util.ErrorMessageUtil;
import com.svalero.TiendaVideojuegos.exception.ErrorMessage;
import com.svalero.TiendaVideojuegos.domain.Shop;
import com.svalero.TiendaVideojuegos.domain.dto.*;
import com.svalero.TiendaVideojuegos.exception.EmployeeNotFoundException;
import com.svalero.TiendaVideojuegos.exception.ShopNotFoundException;
import com.svalero.TiendaVideojuegos.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    private final Logger logger = LoggerFactory.getLogger(ShopController.class);


    @GetMapping("/shops")
    public ResponseEntity<List<ShopOutDTO>> getShop(@RequestParam Map<String, String> data) throws ShopNotFoundException {
        logger.info("GET Shops");

        if (data.isEmpty()) {
            logger.info("Showing all shops");
            return ResponseEntity.ok(shopService.findAll());
        } else if(data.containsKey("id")) {
            logger.info("id: " + data.get("id"));
            List<ShopOutDTO> shop = new ArrayList<>();
            shop.add(shopService.findById(Long.parseLong(data.get(("id")))));
            logger.info("Showing all shops by ID");
            return ResponseEntity.ok(shop);
        }else if(data.containsKey("name")) {
            logger.info("Name: " + data.get("name"));
            List<ShopOutDTO> shop = shopService.findByName(data.get(("name")));
            logger.info("Showing all shops by name");
            return ResponseEntity.ok(shop);
        } else if(data.containsKey("adress")) {
            logger.info("Adress: " + data.get("adress"));
            List<ShopOutDTO> shop = shopService.findByAdress(data.get(("adress")));
            logger.info("Showing all shops by adress");
            return ResponseEntity.ok(shop);
        } else if(data.containsKey("tlf")) {
            logger.info("tlf: " + data.get("tlf"));
            List<ShopOutDTO> shop = shopService.findByTlf(data.get(("tlf")));
            logger.info("Showing all shops by tlf");
            return ResponseEntity.ok(shop);
        }
        logger.info("Bad Request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //@GetMapping("/shops/{id}")
    //public ResponseEntity<Shop> getProduct(@PathVariable long id) throws ShopNotFoundException {
    //    logger.info("Showing all products by ID");
    //    Shop shop = shopService.findById(id);
    //    logger.info("GET END");
    //    return ResponseEntity.ok(shop);
    //}

    //Pasamos el ID DE EMPLEADO
    //TODO: pasar a employees
    /*@GetMapping ("/employees/{id}/shops")
    public ResponseEntity<Shop> getShopByEmployee(@PathVariable long id){
        logger.info("GET Shop by Employee");
        Shop shops = shopService.findByEmployee(id);
        logger.info("GET END");
        return ResponseEntity.ok(shops);
    }*/

    //Pasamos el ID DE EMPLEADO
    @PostMapping("/shops")
    public ResponseEntity<Shop> addShop(@RequestBody ShopInDTO shopInDTO) throws EmployeeNotFoundException{
        logger.info("POST Adding shop" + shopInDTO);
        Shop shop = shopService.addShop(shopInDTO);
        logger.info("POST END");
        return ResponseEntity.status(HttpStatus.OK).body(shop);
    }

    @DeleteMapping("/shops/{id}")
    public ResponseEntity<Void> deleteShop(@PathVariable long id) throws ShopNotFoundException {
        logger.info("DELETE shop");
        shopService.deleteShop(id);
        logger.info("DELETE END");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/shops/{id}")
    public ResponseEntity<Shop> modifyShop(@PathVariable long id, @RequestBody Shop shop) throws ShopNotFoundException {
        logger.info("PUT modify Shop");

        Shop modShop = shopService.modifyShop(id, shop);
        logger.info("PUT END");

        return ResponseEntity.status(HttpStatus.OK).body(modShop);
    }

    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleShopNotFoundException(ShopNotFoundException snfe) {
        logger.error(snfe.getMessage(), snfe);
        ErrorMessage errorMessage = new ErrorMessage(404, snfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEmployeeNotFoundException(EmployeeNotFoundException enfe) {
        logger.error(enfe.getMessage(), enfe);
        ErrorMessage errorMessage = new ErrorMessage(404, enfe.getMessage());
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException cve){
        logger.error("Constraint violation");
        return ErrorMessageUtil.getErrorExceptionResponseEntity(cve);
    }

}
