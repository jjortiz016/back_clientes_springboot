package com.orbitecsl.springbootbackendapirest.models.services;
import com.orbitecsl.springbootbackendapirest.models.dao.IRepositoryClienteDao;
import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import com.orbitecsl.springbootbackendapirest.models.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImple implements IClienteService{

    @Autowired
    IRepositoryClienteDao iRepositoryClienteDao;
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List <Cliente>) iRepositoryClienteDao.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable peageable) {
        return iRepositoryClienteDao.findAll(peageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return iRepositoryClienteDao.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return iRepositoryClienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        iRepositoryClienteDao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegiones() {
        return iRepositoryClienteDao.findAllRegiones();
    }

}
