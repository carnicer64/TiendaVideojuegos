package com.svalero.TiendaVideojuegos.service;

import com.svalero.TiendaVideojuegos.domain.Employee;
import com.svalero.TiendaVideojuegos.domain.dto.EmployeeInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.EmployeeOutDTO;
import com.svalero.TiendaVideojuegos.exception.EmployeeNotFoundException;

import java.util.List;


public interface EmployeeService {
    List<EmployeeOutDTO> findAll();

    List<EmployeeOutDTO> findByName(String name);

    List<EmployeeOutDTO> findByEmail(String email);

    EmployeeOutDTO findById(long id) throws EmployeeNotFoundException;

    List<EmployeeOutDTO> findByBoss(boolean boss);

    Employee addEmployee(EmployeeInDTO employeeInDTO);

    void deleteEmployee(long id) throws EmployeeNotFoundException ;

    Employee modifyEmployee(long id, Employee employee) throws EmployeeNotFoundException;
}
