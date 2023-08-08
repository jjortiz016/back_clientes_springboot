package com.orbitecsl.springbootbackendapirest.controllers;

import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import com.orbitecsl.springbootbackendapirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins={"http://localhost:4200/"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
     @Autowired
     private IClienteService iClienteService;

     @GetMapping("/clientes")
     public List<Cliente> index(){
         return iClienteService.findAll();
    }

    @GetMapping("clientes/{id}")
    public Cliente show(@PathVariable Long id){
         return iClienteService.findById(id);
    }

    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)  //201 QUE FUE CREADO
    public Cliente create(@RequestBody Cliente cliente){

         return iClienteService.save(cliente);

    }
    @PutMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id){
         Cliente clienteActual= iClienteService.findById(id);
         clienteActual.setNombre(cliente.getNombre());
         clienteActual.setApellido(cliente.getApellido());
         clienteActual.setEmail(cliente.getEmail());
          return iClienteService.save(clienteActual);
    }

    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
         iClienteService.delete(id);
    }
}
