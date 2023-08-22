package com.svalero.TiendaVideojuegos.controller;

import com.svalero.TiendaVideojuegos.Util.ErrorMessageUtil;
import com.svalero.TiendaVideojuegos.exception.ErrorMessage;
import com.svalero.TiendaVideojuegos.domain.Product;
import com.svalero.TiendaVideojuegos.domain.dto.ProductInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.ProductOutDTO;
import com.svalero.TiendaVideojuegos.exception.ProductNotFoundException;
import com.svalero.TiendaVideojuegos.service.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products")
    public ResponseEntity<List<ProductOutDTO>> getProducts(@RequestParam Map<String, String> data) throws ProductNotFoundException {
        logger.info("GET Product");

        if (data.isEmpty()) {
            logger.info("Showing all products");
            return ResponseEntity.ok(productService.findAll());
        } else if(data.containsKey("id")) {
            logger.info("id: " + data.get("id"));
            List<ProductOutDTO> product = new ArrayList<>();
            product.add(productService.findById(Long.parseLong(data.get(("id")))));
            logger.info("Showing all products by ID");
            return ResponseEntity.ok(product);
        } else if(data.containsKey("name")) {
            logger.info("Name: " + data.get("name"));
            List<ProductOutDTO> products = productService.findByName(data.get(("name")));
            logger.info("Showing all products by name");
            return ResponseEntity.ok(products);
        } else if(data.containsKey("cost")) {
            logger.info("Cost price: " + data.get("cost"));
            List<ProductOutDTO> products = productService.findByCost(Double.parseDouble(data.get(("cost"))));
            logger.info("Showing all products by cost price");
            return ResponseEntity.ok(products);
        } else if (data.containsKey("sale")) {
            logger.info("Sale price: " + data.get("sale"));
            List<ProductOutDTO> products = productService.findBySale(Double.parseDouble(data.get(("sale"))));
            logger.info("Showing all products by sale price");
            return ResponseEntity.ok(products);
        }
        logger.info("Bad Request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*@GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) throws ProductNotFoundException {
        logger.info("Showing all products by ID");
        Product product = productService.findById(id);
        logger.info("GET END");
        return ResponseEntity.ok(product);
    }*/

    @PostMapping("/products")
        public ResponseEntity<Product> addProduct(@RequestBody ProductInDTO productInDTO){
        logger.info("POST Adding product - addProduct" + productInDTO);
        Product product = productService.addProduct(productInDTO);
        logger.info("POST addProduct - end");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) throws ProductNotFoundException {
        logger.info("DELETE product");
        productService.deleteProduct(id);
        logger.info("DELETE END");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> modifyProduct(@PathVariable long id, @RequestBody Product product) throws ProductNotFoundException {
        logger.info("PUT modify Product");
        Product newProduct = productService.modifyProduct(id, product);
        logger.info("PUT END");
        return ResponseEntity.status(HttpStatus.OK).body(newProduct);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ProductNotFoundException pnfe) {
        logger.error(pnfe.getMessage(), pnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, pnfe.getMessage());
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
