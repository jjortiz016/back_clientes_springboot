package com.orbitecsl.springbootbackendapirest.auth;

import com.orbitecsl.springbootbackendapirest.models.entity.Usuario;
import com.orbitecsl.springbootbackendapirest.models.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class InfoAdicionalToken implements TokenEnhancer {
    @Autowired
    private IUsuarioService usuarioService;
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Usuario usuario = usuarioService.findByUsername(authentication.getName());

        Map <String, Object> info = new HashMap<>();
        info.put("info_adicional", "Hola que tal".concat(authentication.getName()));
        info.put("Nombre", usuario.getNombre());
        info.put("Apellido", usuario.getApellido());
        info.put("Email", usuario.getEmail());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
