package com.orbitecsl.springbootbackendapirest.controllers;

import com.orbitecsl.springbootbackendapirest.models.entity.Vehiculo;
import com.orbitecsl.springbootbackendapirest.models.services.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins={"http://localhost:4200/"})
@RestController
@RequestMapping("/api")
public class VehiculoRestController {
   @Autowired
    private IVehiculoService iVehiculoService;

    @GetMapping("/vehiculos")
    public List<Vehiculo> index(){
        return iVehiculoService.findAll();
    }

    @GetMapping("/vehiculos/page/{page}")
    public Page<Vehiculo> index(@PathVariable Integer page){
        Pageable pageable= PageRequest.of(page, 4);
        return iVehiculoService.findAll(pageable);

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
    public ResponseEntity<?> create(@Valid @RequestBody Vehiculo vehiculo, BindingResult result){
        Vehiculo vehiculoNew= null;
        Map<String, Object> response= new HashMap<>();
         if(result.hasErrors()){
             List<String> errors = new ArrayList<>();
             for(FieldError err: result.getFieldErrors()){
                errors.add("El campo '"+ err.getField()+"' "+err.getDefaultMessage());
             }
             response.put("errors", errors);
             return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
         }


        try{
            vehiculoNew=iVehiculoService.save(vehiculo);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El vehiculo ha sido creado con exito");
        response.put("vehiculo", vehiculo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/vehiculos/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Vehiculo vehiculo, BindingResult result, @PathVariable Long id){
        Vehiculo vehiculoActual= iVehiculoService.findById(id);
        Vehiculo vehiculoUpdated= null;
        Map<String, Object> response = new HashMap<>();
         if(result.hasErrors()){
             List <String> errors= result.getFieldErrors()
                     .stream()
                     .map(err -> "El campo '"+ err.getField()+"' "+err.getDefaultMessage())
                     .collect(Collectors.toList());
             response.put("errors", errors);
             return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
         }

        if(vehiculoActual==null){
            response.put("mensaje"," El vehiculo ID: ".concat(id.toString().concat(" No existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            vehiculoActual.setPlaca(vehiculo.getPlaca());
            vehiculoActual.setMarca(vehiculo.getMarca());
            vehiculoActual.setColor(vehiculo.getColor());
            vehiculoActual.setTipo(vehiculo.getTipo());
            vehiculoUpdated= iVehiculoService.save(vehiculoActual);

        }catch(DataAccessException e){
            response.put("mensaje", "Error al realizar el update en la base de datos");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El vehiculo ha sido actualizado con exito!!");
        response.put("vehiculo", vehiculoActual);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/vehiculos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try{
            iVehiculoService.delete(id);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al eliminar el registro del vehiculo");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El vehiculo fue eliminado con exito!!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
