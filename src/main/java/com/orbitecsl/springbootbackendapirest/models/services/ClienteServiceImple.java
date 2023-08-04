package com.orbitecsl.springbootbackendapirest.models.services;

import com.orbitecsl.springbootbackendapirest.models.dao.IRepositoryClienteDao;
import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
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


}
