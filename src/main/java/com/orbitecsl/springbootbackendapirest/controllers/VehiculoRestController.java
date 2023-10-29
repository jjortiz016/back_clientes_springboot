package com.orbitecsl.springbootbackendapirest.controllers;

import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import com.orbitecsl.springbootbackendapirest.models.services.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/vehiculos/{id}")
    public Vehiculo show (@PathVariable Long id){
        return iVehiculoService.findById(id);
    }

    @PostMapping("/vehiculos")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehiculo create(@RequestBody Vehiculo vehiculo){
        return iVehiculoService.save(vehiculo);
    }

    @PutMapping("/vehiculos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Vehiculo update(@RequestBody Vehiculo vehiculo, @PathVariable Long id){
        Vehiculo vehiculoActual= iVehiculoService.findById(id);
        vehiculoActual.setPlaca(vehiculo.getPlaca());
        vehiculoActual.setMarca(vehiculo.getMarca());
        vehiculoActual.setColor(vehiculo.getColor());
        vehiculoActual.setTipo(vehiculo.getTipo());
        return iVehiculoService.save(vehiculoActual);
    }
    @DeleteMapping("/vehiculos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        iVehiculoService.delete(id);
    }


}
