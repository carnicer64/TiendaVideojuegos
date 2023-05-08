package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Product;
import com.svalero.TiendaVideojuegos.domain.dto.ProductInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.ProductOutDTO;
import com.svalero.TiendaVideojuegos.exception.ProductNotFoundException;
import com.svalero.TiendaVideojuegos.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);



    @Override
    public List<ProductOutDTO> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductOutDTO> productOutDTO = modelMapper.map(products, new TypeToken<List<ProductOutDTO>>() {}.getType());

        return productOutDTO;
    }

    @Override
    public List<ProductOutDTO> findByName(String name) {
        logger.info("Name: " + name);
        List<Product> products = productRepository.findAllByName(name);
        List<ProductOutDTO> productOutDTO = modelMapper.map(products, new TypeToken<List<ProductOutDTO>>() {}.getType());
        return productOutDTO;
    }

    @Override
    public List<ProductOutDTO> findByCost(String cost) {
        logger.info("Cost price: " + cost);
        List<Product> products = productRepository.findAllByCost(cost);
        List<ProductOutDTO> productOutDTO = modelMapper.map(products, new TypeToken<List<ProductOutDTO>>() {}.getType());
        return productOutDTO;
    }

    @Override
    public List<ProductOutDTO> findBySale(String sale) {
        logger.info("Sale price: " + sale);
        List<Product> products = productRepository.findAllBySale(sale);
        List<ProductOutDTO> productOutDTO = modelMapper.map(products, new TypeToken<List<ProductOutDTO>>() {}.getType());
        return productOutDTO;
    }

    @Override
    public Product findById(long id) throws ProductNotFoundException {
        logger.info("ID: " + id);

        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Product addProduct(ProductInDTO productInDTO) {
        logger.info("Creating the product");

        Product newProduct = new Product();
        modelMapper.map(productInDTO, newProduct);

        return productRepository.save(newProduct);
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

                productRepository.delete(product);
    }

    @Override
    public Product modifyProduct(long id, Product product) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        modelMapper.map(product, existingProduct);
        existingProduct.setId(id);

        return productRepository.save(existingProduct);
    }
}
