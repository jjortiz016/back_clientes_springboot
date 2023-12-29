package com.orbitecsl.springbootbackendapirest.models.services;

import com.orbitecsl.springbootbackendapirest.models.entity.Usuario;

public interface IUsuarioService {
    public Usuario findByUsername(String username);


}
