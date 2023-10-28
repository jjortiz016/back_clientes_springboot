package com.orbitecsl.springbootbackendapirest.controllers;

import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import com.orbitecsl.springbootbackendapirest.models.services.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoRestController {
    @Autowired
    private IVehiculoService iVehiculoService;

    @GetMapping("/index")
    public List<Vehiculo> index(){
        return iVehiculoService.findAll();
    }

}
