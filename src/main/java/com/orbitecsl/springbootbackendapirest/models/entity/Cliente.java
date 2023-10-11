package com.orbitecsl.springbootbackendapirest.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

     @NotEmpty(message="No debe esta vacio!")
     @Size(min=4, max=15, message = "El tamaño tiene que estar entra 4 y 15 caracteres!")
     @Column(nullable=false)
     private String nombre;
    @NotEmpty(message="No debe esta vacio!")
     private String apellido;
    @NotEmpty(message="No debe esta vacio!")
    @Email(message="El email digitado no es una dirección bien formada!")
    @Column(nullable=false, unique=true)
     private String email;

    @NotNull(message= "No puede estar vacio")
    @Column(name="nacimiento")
    @Temporal(TemporalType.DATE)
    private Date nacimiento;
    @Column(name="foto")
    private String foto;

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

    public Date getNacimiento() {
        return nacimiento;
    }
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", nacimiento=" + nacimiento +
                ", foto='" + foto + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
