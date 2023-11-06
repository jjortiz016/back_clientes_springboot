package com.orbitecsl.springbootbackendapirest.controllers;

import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import com.orbitecsl.springbootbackendapirest.models.services.IVehiculoService;
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
@RequestMapping("/api2")
public class VehiculoRestController {
   @Autowired
    private IVehiculoService iVehiculoService;

    @GetMapping("/vehiculos")
    public List<Vehiculo> index(){
        return iVehiculoService.findAll();
    }

    @GetMapping("/vehiculos/{id}")
    public ResponseEntity<?> show (@PathVariable Long id){
        Vehiculo vehiculo=null;
        Map<String, Object> response= new HashMap<>();
        try {
            vehiculo= iVehiculoService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos.");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(vehiculo==null){
            response.put("mensaje","El vehiculo ID:".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
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
