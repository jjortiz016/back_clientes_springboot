package com.orbitecsl.springbootbackendapirest.models.dao;

import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryClienteDao extends CrudRepository<Cliente, Long> {
}
