package com.orbitecsl.springbootbackendapirest.models.dao;

import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryVehiculoDao extends JpaRepository<Vehiculo, Long> {

}
