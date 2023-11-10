package com.orbitecsl.springbootbackendapirest.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table (name="vehiculos")
public class Vehiculo implements Serializable {
    private static final long serialVersionUID= 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message= " no puede estar vacio.")
    @Size(min=4, max=6, message= " debe tener entre 4 y 6 caracteres.")
    @Column(length = 30, nullable=false, unique=true)
    private String placa;
    @Size(max=40, message = " no debe superar los 40 caracteres.")
    @Column(length = 40)
    private String marca;

    @Size(max=40, message = " no debe superar los 40 caracteres.")
    @Column(length = 40)
    private String color;
    @NotEmpty(message= " no puede estar vacio.")
    @Size(max=45, message = " no debe superar los 45 caracteres.")
    @Column(length = 45, nullable = false)
    private String tipo;

    @Column(name= "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @PrePersist
    public void prepersist(){
        this.createAt= new Date();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
