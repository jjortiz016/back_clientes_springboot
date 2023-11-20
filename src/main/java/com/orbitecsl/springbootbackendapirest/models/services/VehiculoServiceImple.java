package com.orbitecsl.springbootbackendapirest.models.services;
import com.orbitecsl.springbootbackendapirest.models.dao.IRepositoryVehiculosDao;
import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class VehiculoServiceImple implements IVehiculoService {
    @Autowired
    IRepositoryVehiculosDao iRepositoryVehiculoDao;


   @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findAll() {
       return iRepositoryVehiculoDao.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo findById(Long id) {
        return iRepositoryVehiculoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Vehiculo> findAll(Pageable pageable) {
        return iRepositoryVehiculoDao.findAll(pageable);
    }

    @Override
    @Transactional
    public Vehiculo save(Vehiculo vehiculo) {
        return iRepositoryVehiculoDao.save(vehiculo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
       iRepositoryVehiculoDao.deleteById(id);
    }
}
