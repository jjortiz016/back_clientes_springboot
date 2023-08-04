package com.orbitecsl.springbootbackendapirest.controllers;

import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import com.orbitecsl.springbootbackendapirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
     @Autowired
     private IClienteService iClienteService;

     @GetMapping("/clientes")
     public List<Cliente> index(){
         return iClienteService.findAll();
    }

}
