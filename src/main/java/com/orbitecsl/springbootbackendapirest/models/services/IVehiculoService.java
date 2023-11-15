package com.orbitecsl.springbootbackendapirest.models.services;
import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVehiculoService {
    public List<Vehiculo> findAll();

    public Vehiculo findById(Long id);
    public Page<Vehiculo> findAll(Pageable pageable);
    public Vehiculo save(Vehiculo vehiculo);

    public void delete(Long id);


}
