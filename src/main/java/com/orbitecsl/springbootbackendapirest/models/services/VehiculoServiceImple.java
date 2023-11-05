package com.orbitecsl.springbootbackendapirest.models.services;

import com.orbitecsl.springbootbackendapirest.models.dao.IRepositoryVehiculoDao;
import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImple implements IVehiculoService {
    @Autowired
    IRepositoryVehiculoDao iRepositoryVehiculoDao;


   @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findAll() {
       return (List<Vehiculo>) iRepositoryVehiculoDao.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo findById(Long id) {
        return iRepositoryVehiculoDao.findById(id).orElse(null);
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        return iRepositoryVehiculoDao.save(vehiculo);
    }

    @Override
    public void delete(Long id) {
       iRepositoryVehiculoDao.deleteById(id);
    }
}
