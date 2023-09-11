package com.orbitecsl.springbootbackendapirest.models.dao;

import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryClienteDao extends JpaRepository<Cliente, Long> {
}
