package com.orbitecsl.springbootbackendapirest.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="clientes")  // el nombre de la tabla va en plural y minuscula si es compuesta se separa co guion
public class Cliente implements Serializable {
      private static final long serialVersionUID= 1L; // es requerido cuando se implementa el serializable
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
     private Long id;
     private String nombre;
     private String apellido;
     private String email;
     @Column(name="create_at")
     @Temporal(TemporalType.DATE)  //para transformar la fecha de java a la fecha de mysql
     private Date createAt;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
