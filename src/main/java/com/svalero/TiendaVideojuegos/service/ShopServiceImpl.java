package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Employee;
import com.svalero.TiendaVideojuegos.domain.Shop;
import com.svalero.TiendaVideojuegos.domain.dto.*;
import com.svalero.TiendaVideojuegos.exception.EmployeeNotFoundException;
import com.svalero.TiendaVideojuegos.exception.ShopNotFoundException;
import com.svalero.TiendaVideojuegos.repository.EmployeeRepository;
import com.svalero.TiendaVideojuegos.repository.ShopRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Override
    public List<ShopOutDTO> findAll() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopOutDTO> shopOutDTO = modelMapper.map(shops, new TypeToken<List<ShopOutDTO>>() {}.getType());

        return shopOutDTO;
    }

    @Override
    public List<ShopOutDTO> findByName(String name) {
        logger.info("Name: " + name);
        List<Shop> shops = shopRepository.findByName(name);
        List<ShopOutDTO> shopOutDTO = modelMapper.map(shops, new TypeToken<List<ShopOutDTO>>() {}.getType());
        return shopOutDTO;
    }

    @Override
    public List<ShopOutDTO> findByAdress(String adress) {
        logger.info("Adress: " + adress);
        List<Shop> shops = shopRepository.findByAdress(adress);
        List<ShopOutDTO> shopOutDTO = modelMapper.map(shops, new TypeToken<List<ShopOutDTO>>() {}.getType());
        return shopOutDTO;
    }

    @Override
    public List<ShopOutDTO> findByTlf(String tlf) {
        logger.info("Tlf: " + tlf);
        List<Shop> shops = shopRepository.findByTlf(tlf);
        List<ShopOutDTO> shopOutDTO = modelMapper.map(shops, new TypeToken<List<ShopOutDTO>>() {}.getType());
        return shopOutDTO;
    }

    @Override
    public Shop findById(long id) throws ShopNotFoundException {
        logger.info("ID: " + id);

        return shopRepository.findById(id).orElseThrow(ShopNotFoundException::new);
    }


    @Override
    public Shop findByEmployee(long id) {
        logger.info("IDEmployee: " + id);

        return shopRepository.findByEmployee_Id(id);
    }

    @Override
    public Shop addShop(long id, ShopInDTO shopInDTO) throws EmployeeNotFoundException {
        logger.info("Creating shop");

        Shop newShop = new Shop();
        modelMapper.map(shopInDTO, newShop);

        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        newShop.setEmployee(employee);

        return shopRepository.save(newShop);
    }

    @Override
    public void deleteShop(long id) throws ShopNotFoundException {
        Shop shop = shopRepository.findById(id).orElseThrow(ShopNotFoundException::new);

        shopRepository.delete(shop);
    }

    @Override
    public Shop modifyShop(long id, Shop shop) throws ShopNotFoundException {
        logger.info("Modifying shop");

        Shop existingShop = shopRepository.findById(id).orElseThrow(ShopNotFoundException::new);

        modelMapper.map(shop, existingShop);
        existingShop.setId(id);

        return shopRepository.save(existingShop);
    }
}
