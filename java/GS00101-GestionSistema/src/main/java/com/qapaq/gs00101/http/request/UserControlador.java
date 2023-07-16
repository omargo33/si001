package com.qapaq.gs00101.http.request;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Objeto para dar soporte a servicio REST de conociendo el usuario.
 * 
 * @author o.velez@qapaq.io
 * @since 2023-07-16
 * 
 * @see "https://www.baeldung.com/spring-security-map-authorities-jwt"
 * 
 */
@RestController
@RequestMapping(value = "/v1/usuarios")
public class UserControlador {

    @GetMapping("/usuario")
    public Map<String,Object> userInfo(JwtAuthenticationToken principal) {

        Collection<String> authorities = principal.getAuthorities()
          .stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.toList());
        
        Map<String,Object> info = new HashMap<>();
        info.put("name", principal.getName());
        info.put("authorities", authorities);
        info.put("tokenAttributes", principal.getTokenAttributes());
        
        return info;
    }
}
