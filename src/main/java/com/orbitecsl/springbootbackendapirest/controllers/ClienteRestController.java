package com.orbitecsl.springbootbackendapirest.controllers;

import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import com.orbitecsl.springbootbackendapirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> show(@PathVariable Long id){  //<?> tipo generic
        Cliente cliente= null;
        Map<String, Object> response = new HashMap<>();
         try {  // controlas si hay un error con la base de datos..
             cliente=  iClienteService.findById(id);
         }catch (DataAccessException e){
             response.put("mensaje","Error al realizar la consulta en la base de datos");
             response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
             return new ResponseEntity< Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
         }


         if(cliente==null){
             response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" no existe en la base de datos")));
             return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
         }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
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
