package com.orbitecsl.springbootbackendapirest.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="clientes")  // el nombre de la tabla va en plural y minuscula si es compuesta se separa co guion
public class Cliente implements Serializable {
      private static final long serialVersionUID= 1L; // es requerido cuando se implementa el serializable
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
     private Long id;

     @NotEmpty
     @Size(min=4, max=15)
     @Column(nullable=false)
     private String nombre;
    @NotEmpty
     private String apellido;
    @NotEmpty
    @Email
    @Column(nullable=false, unique=true)
     private String email;
     @Column(name="create_at")
     @Temporal(TemporalType.DATE)  //para transformar la fecha de java a la fecha de mysql
     private Date createAt;

     @PrePersist
     public void prePersist(){
         this.createAt= new Date();
     }

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
