package com.svalero.TiendaVideojuegos.repository;

import com.svalero.TiendaVideojuegos.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findAll();

    List<Client> findByName(String name);
}
