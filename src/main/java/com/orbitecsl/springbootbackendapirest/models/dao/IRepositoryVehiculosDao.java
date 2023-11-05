package com.orbitecsl.springbootbackendapirest.models.dao;

import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryVehiculosDao extends JpaRepository<Vehiculo, Long> {
}
