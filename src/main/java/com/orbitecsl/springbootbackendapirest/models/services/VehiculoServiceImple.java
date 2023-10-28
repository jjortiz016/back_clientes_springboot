package com.orbitecsl.springbootbackendapirest.models.services;

import com.orbitecsl.springbootbackendapirest.models.dao.IRepositoryVehiculoDao;
import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class VehiculoServiceImple implements IVehiculoService {
    @Autowired
    IRepositoryVehiculoDao iRepositoryVehiculoDao;


    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findAll() {
       return (List<Vehiculo>) iRepositoryVehiculoDao.findAll();

    }
}
