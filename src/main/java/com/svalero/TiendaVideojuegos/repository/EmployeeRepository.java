package com.svalero.TiendaVideojuegos.repository;


import com.svalero.TiendaVideojuegos.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();

    List<Employee> findByName(String name);

    List<Employee> findByEmail(String email);

    List<Employee> findByBossEquals(boolean boss);
}
