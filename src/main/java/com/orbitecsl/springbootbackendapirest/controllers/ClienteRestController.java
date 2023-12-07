package com.orbitecsl.springbootbackendapirest.controllers;

import com.orbitecsl.springbootbackendapirest.models.entity.Cliente;
import com.orbitecsl.springbootbackendapirest.models.services.IClienteService;

import com.orbitecsl.springbootbackendapirest.models.services.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins={"http://localhost:4200/"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
    private static final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
     @Autowired
     private IClienteService iClienteService;

     @Autowired
     private IUploadFileService iUploadFileService;

     @GetMapping("/clientes")
     public List<Cliente> index(){
         return iClienteService.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page){
         Pageable pageable= PageRequest.of(page, 4); // el 4 es la cantidad desplegada
        return iClienteService.findAll(pageable);
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
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result){

          Cliente clienteNew=  null;

          Map<String, Object> response= new HashMap<>();

          if(result.hasErrors()){
           /*   List<String> errors = new ArrayList<>();
              for(FieldError err: result.getFieldErrors()){
                  errors.add("El campo '"+ err.getField()+"' "+err.getDefaultMessage());
              }*/

             //******* nueva forma a partir de java 8**********
              List <String> errors  = result.getFieldErrors()
                              .stream()
                              .map(err -> "El campo '"+ err.getField()+"' "+err.getDefaultMessage())
                              .collect(Collectors.toList());

              response.put("errors", errors);
              return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
          }

          try{
              clienteNew=iClienteService.save(cliente);

          }catch (DataAccessException e){
              response.put("mensaje","Error al realizar el insert  en la base de datos");
              response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
              return new ResponseEntity< Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

          }
         response.put("mensaje","El cliente ha sido creado con exito");
          response.put("cliente",clienteNew);
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id){
        Cliente clienteActual=iClienteService.findById(id);
        Cliente clienteUpdated= null;
        Map<String, Object> response= new HashMap<>();

        if(result.hasErrors()){
            List <String> errors =result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+ err.getField()+"' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

         if(clienteActual==null){
             response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" No existe en la base de datos")));
             return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
         }
        try{
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setNacimiento(cliente.getNacimiento());
            clienteActual.setCreateAt(cliente.getCreateAt());
            clienteUpdated= iClienteService.save(clienteActual);
        }catch (DataAccessException e){
            response.put("mensaje","Error al actualizar el registro del cliente");
            response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

         response.put("mensaje","El cliente ha sido actualizado con exito");
         response.put("cliente",clienteActual);
          return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
         Map<String, Object> response = new HashMap<>();
         try{
             Cliente cliente = iClienteService.findById(id);
             String nombreFotoAnterior= cliente.getFoto();
             iUploadFileService.eliminar(nombreFotoAnterior);
             iClienteService.delete(id);
         }catch (DataAccessException e){
             response.put("mensaje","Error al eliminar el registro del cliente");
             response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
             return new ResponseEntity<Map<String, Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR);
         }
         response.put("mensaje","El cliente eliminado con éxitor!");
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo")MultipartFile archivo, @RequestParam("id") Long id){
         Map<String, Object> response = new HashMap<>();
         Cliente cliente = iClienteService.findById(id);

         if(!archivo.isEmpty()){
             String nombreArchivo = null;
            try{
                 nombreArchivo= iUploadFileService.copiar(archivo);
             }catch (IOException e){
                 response.put("mensaje","Error al subir la imagen del cliente ");
                 response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));

                 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
             }
             String nombreFotoAnterior= cliente.getFoto();

             iUploadFileService.eliminar(nombreFotoAnterior);

             cliente.setFoto(nombreArchivo);
             iClienteService.save(cliente);
             response.put("cliente", cliente);
             response.put("mensaje", "Has subido correctamente la imagen!!"+ nombreArchivo);
         }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{nombreFoto:.+}") //los 2 puntos mas el punto y mas + son para indicar que biene con una extensión.
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

         Resource recurso = null;
          try{
              recurso = iUploadFileService.cargar(nombreFoto);
          }catch (MalformedURLException e){
              e.printStackTrace();
          }

              HttpHeaders cabecera = new HttpHeaders();
              cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename()+ "\"");
             return  new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }


}
