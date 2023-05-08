package com.svalero.TiendaVideojuegos.controller;

import com.svalero.TiendaVideojuegos.Util.ErrorMessage;
import com.svalero.TiendaVideojuegos.domain.Shop;
import com.svalero.TiendaVideojuegos.domain.Stock;
import com.svalero.TiendaVideojuegos.domain.dto.ShopInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.StockInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.StockOutDTO;
import com.svalero.TiendaVideojuegos.exception.EmployeeNotFoundException;
import com.svalero.TiendaVideojuegos.exception.ProductNotFoundException;
import com.svalero.TiendaVideojuegos.exception.ShopNotFoundException;
import com.svalero.TiendaVideojuegos.exception.StockNotFoundException;
import com.svalero.TiendaVideojuegos.service.StockService;
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
public class StockController {

    @Autowired
    private StockService stockService;

    private final Logger logger = LoggerFactory.getLogger(StockController.class);


    @GetMapping("/stocks")
    public ResponseEntity<List<StockOutDTO>> getStocks(@RequestParam Map<String, String> data) {
        logger.info("GET Shops");

        if (data.isEmpty()) {
            logger.info("Showing all stocks");
            return ResponseEntity.ok(stockService.findAll());
        } else if(data.containsKey("amount")) {
            logger.info("Amount: " + data.get("Amount"));
            List<StockOutDTO> stock = stockService.findByAmount(data.get(("amount")));
            logger.info("Showing all shops by name");
            return ResponseEntity.ok(stock);
        } else if(data.containsKey("note")) {
            logger.info("Note: " + data.get("note"));
            List<StockOutDTO> stock = stockService.findByNote(data.get(("stock")));
            logger.info("Showing all shops by note");
            return ResponseEntity.ok(stock);
        }
        logger.info("Bad Request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable long id) throws StockNotFoundException {
        logger.info("Showing all stock by ID");
        Stock stock = stockService.findById(id);
        logger.info("GET END");
        return ResponseEntity.ok(stock);
    }

    @GetMapping ("/products/{id}/stocks")
    public ResponseEntity<List<Stock>> getStockByProduct(@PathVariable long id){
        logger.info("GET Stock by product id");
        List<Stock> stock = stockService.findByProduct(id);
        logger.info("GET END");
        return ResponseEntity.ok(stock);
    }

    @GetMapping ("/shops/{id}/stocks")
    public ResponseEntity<Stock> getStockByShop(@PathVariable long id){
        logger.info("GET Stock by shop id");
        Stock stock = stockService.findByShop(id);
        logger.info("GET END");
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/stocks/{id}")
    public ResponseEntity<Stock> addStock(@RequestBody StockInDTO stockInDTO) throws ProductNotFoundException, ShopNotFoundException {
        logger.info("POST Adding stock");
        Stock stock = stockService.addStock(stockInDTO);
        logger.info("POST END");
        return ResponseEntity.status(HttpStatus.OK).body(stock);
    }

    @DeleteMapping("/stock")
    public ResponseEntity<Void> deleteStock(@PathVariable long id) throws StockNotFoundException {
        logger.info("DELETE stock");
        stockService.deleteStock(id);
        logger.info("DELETE END");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<Stock> modifyStock(@PathVariable long id, @RequestBody Stock stock) throws StockNotFoundException, ProductNotFoundException, ShopNotFoundException {

        Stock modStock = stockService.modifyStock(id, stock);

        return ResponseEntity.status(HttpStatus.OK).body(modStock);
    }

    @ExceptionHandler(ShopNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleShopNotFoundException(ShopNotFoundException snfe) {
        logger.error(snfe.getMessage(), snfe);
        ErrorMessage errorMessage = new ErrorMessage(404, snfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFoundException(ProductNotFoundException pnfe) {
        logger.error(pnfe.getMessage(), pnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, pnfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleStockNotFoundException(StockNotFoundException stnfe) {
        logger.error(stnfe.getMessage(), stnfe);
        ErrorMessage errorMessage = new ErrorMessage(404, stnfe.getMessage());
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
