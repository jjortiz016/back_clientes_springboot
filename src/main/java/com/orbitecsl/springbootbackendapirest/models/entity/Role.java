package com.orbitecsl.springbootbackendapirest.models.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="roles")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(unique=true, length=20)
    private String nombre;

    private static final long serialVersionUID = 1L; // atributo statico que maneja el lenguaje por debajo

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
