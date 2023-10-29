package com.orbitecsl.springbootbackendapirest.controllers;

import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import com.orbitecsl.springbootbackendapirest.models.services.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins={"http://localhost:4200/"})
@RestController
@RequestMapping("/api2")
public class VehiculoRestController {
   @Autowired
    private IVehiculoService iVehiculoService;

    @GetMapping("/vehiculos")
    public List<Vehiculo> index(){
        return iVehiculoService.findAll();
    }

    @GetMapping("/clientes/{id)")
    public Vehiculo show (){
        return null;
    }

}
