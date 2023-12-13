package com.orbitecsl.springbootbackendapirest.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique=true, length=20)
    private String username;

    @Column (length = 80)
    private String password;

    private Boolean enabled;

    @ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL) //CascadeType.All para cuando elimine el usuario se elimine los roles relacionados, o cuando se creo el usuairo se cree el role
    //si queremso cambiar el nombre de la tabla intermedia
    @JoinTable(name="users_authorities", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"),
    uniqueConstraints = {@UniqueConstraint(columnNames={"user_id", "role_id"})})
    private List<Role> roles;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
