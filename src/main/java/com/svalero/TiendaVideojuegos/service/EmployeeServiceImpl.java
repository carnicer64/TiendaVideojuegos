package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Employee;
import com.svalero.TiendaVideojuegos.domain.dto.EmployeeInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.EmployeeOutDTO;
import com.svalero.TiendaVideojuegos.domain.dto.ProductOutDTO;
import com.svalero.TiendaVideojuegos.exception.EmployeeNotFoundException;
import com.svalero.TiendaVideojuegos.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);


    public EmployeeServiceImpl() {
        super();
    }

    @Override
    public List<EmployeeOutDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeOutDTO> employeeOutDTO = modelMapper.map(employees, new TypeToken<List<ProductOutDTO>>() {}.getType());

        return employeeOutDTO;
    }

    @Override
    public List<EmployeeOutDTO> findByName(String name) {
        logger.info("Name: " + name);
        List<Employee> employees = employeeRepository.findByName(name);
        List<EmployeeOutDTO> employeeOutDTO = modelMapper.map(employees, new TypeToken<List<EmployeeOutDTO>>() {}.getType());
        return employeeOutDTO;
    }

    @Override
    public List<EmployeeOutDTO> findByEmail(String email) {
        logger.info("Email: " + email);
        List<Employee> employees = employeeRepository.findByEmail(email);
        List<EmployeeOutDTO> employeeOutDTO = modelMapper.map(employees, new TypeToken<List<EmployeeOutDTO>>() {}.getType());
        return employeeOutDTO;
    }

    @Override
    public List<EmployeeOutDTO> findById(String id) throws EmployeeNotFoundException {
        logger.info("ID: " + id);
        List<Employee> employees = employeeRepository.findByEmail(id);
        List<EmployeeOutDTO> employeeOutDTO = modelMapper.map(employees, new TypeToken<List<EmployeeOutDTO>>() {}.getType());
        return employeeOutDTO;
    }

    @Override
    public List<EmployeeOutDTO> findByBoss(boolean boss) {
        List<Employee> employees = employeeRepository.findByBossEquals(boss);
        List<EmployeeOutDTO> employeeOutDTO = modelMapper.map(employees, new TypeToken<List<EmployeeOutDTO>>() {}.getType());
        return employeeOutDTO;
    }

    @Override
    public Employee addEmployee(EmployeeInDTO employeeInDTO) {
        logger.info("Creating the employee");

        Employee newEmployee = new Employee();
        modelMapper.map(employeeInDTO, newEmployee);

        return employeeRepository.save(newEmployee);
    }

    @Override
    public void deleteEmployee(long id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        logger.info("Employee deleted");

        employeeRepository.delete(employee);
    }

    @Override
    public Employee modifyEmployee(long id, Employee employee) throws EmployeeNotFoundException {
        Employee existingProduct = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);

        modelMapper.map(employee, existingProduct);
        existingProduct.setId(id);

        return employeeRepository.save(existingProduct);
    }
}
