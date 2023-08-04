package com.orbitecsl.springbootbackendapirest.models.services;

import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> findAll();

}
