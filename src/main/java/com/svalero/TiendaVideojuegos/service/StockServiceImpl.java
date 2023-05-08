package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Product;
import com.svalero.TiendaVideojuegos.domain.Shop;
import com.svalero.TiendaVideojuegos.domain.Stock;
import com.svalero.TiendaVideojuegos.domain.dto.StockInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.StockOutDTO;
import com.svalero.TiendaVideojuegos.exception.ProductNotFoundException;
import com.svalero.TiendaVideojuegos.exception.ShopNotFoundException;
import com.svalero.TiendaVideojuegos.exception.StockNotFoundException;
import com.svalero.TiendaVideojuegos.repository.ProductRepository;
import com.svalero.TiendaVideojuegos.repository.ShopRepository;
import com.svalero.TiendaVideojuegos.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Override
    public List<StockOutDTO> findAll() {
        List<Stock> stocks = stockRepository.findAll();
        List<StockOutDTO> stockOutDTO = modelMapper.map(stocks, new TypeToken<List<StockOutDTO>>() {}.getType());

        return stockOutDTO;
    }

    @Override
    public List<StockOutDTO> findByNote(String note) {
        logger.info("Note: " + note);
        List<Stock> stocks = stockRepository.findByNote(note);
        List<StockOutDTO> stockOutDTO = modelMapper.map(stocks, new TypeToken<List<StockOutDTO>>() {}.getType());
        return stockOutDTO;
    }

    @Override
    public List<StockOutDTO> findByAmount(String amount) {
        logger.info("Amount: " + amount);
        List<Stock> stocks = stockRepository.findByNote(amount);
        List<StockOutDTO> stockOutDTO = modelMapper.map(stocks, new TypeToken<List<StockOutDTO>>() {}.getType());
        return stockOutDTO;
    }

    @Override
    public Stock findById(long id) throws StockNotFoundException {
        logger.info("ID: " + id);

        return stockRepository.findById(id).orElseThrow(StockNotFoundException::new);
    }

    @Override
    public List<Stock> findByProduct(long id) {
        logger.info("IDProduct: " + id);

        return stockRepository.findByProduct_Id(id);
    }

    @Override
    public Stock findByShop(long id) {
        logger.info("IDShop: " + id);

        return stockRepository.findByShop_Id(id);
    }

    @Override
    public Stock addStock(StockInDTO stockInDTO) throws ProductNotFoundException, ShopNotFoundException {
        logger.info("Creating stock");

        Stock newStock = new Stock();
        modelMapper.map(stockInDTO, newStock);

        Product product = productRepository.findById(stockInDTO.getProduct().getId()).orElseThrow(ProductNotFoundException::new);
        Shop shop = shopRepository.findById(stockInDTO.getShop().getId()).orElseThrow(ShopNotFoundException::new);

        newStock.setProduct(product);
        newStock.setShop(shop);

        return stockRepository.save(newStock);
    }

    @Override
    public void deleteStock(long id) throws StockNotFoundException {
        Stock stock = stockRepository.findById(id).orElseThrow(StockNotFoundException::new);

        stockRepository.delete(stock);
    }

    @Override
    public Stock modifyStock(long id, Stock stock) throws StockNotFoundException{
        logger.info("Modifying stock");

        Stock existingStock = stockRepository.findById(id).orElseThrow(StockNotFoundException::new);

        modelMapper.map(stock, existingStock);
        existingStock.setId(id);

        return stockRepository.save(existingStock);
    }
}
