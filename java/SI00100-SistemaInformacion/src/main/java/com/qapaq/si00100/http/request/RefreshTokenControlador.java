package com.qapaq.si00100.http.request;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.http.request.Common.ComonRefreshTokenControlador;
import com.qapaq.http.request.Common.TokenRefreshControlador;
import com.qapaq.si00100.servicio.MonitorServicio;

import lombok.RequiredArgsConstructor;

/**
 * Clase para controlar las peticiones de las ciudades.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 * @see https://www.youtube.com/watch?v=VVn9OG9nfH0
 * @see security
 * 
 */
@RestController
@RequestMapping(value = "/login")
@RequiredArgsConstructor
public class RefreshTokenControlador extends ComonRefreshTokenControlador implements TokenRefreshControlador {

    @Autowired
    private MonitorServicio monitorServicio;

    
    @Override
    public boolean isActiveUser(String username) {        
        return monitorServicio.existsMonitorByNombre(username);
    }

    @Override
    public List<String> getRoles(String username) {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_MONITOR");
        return roles;
    }

    /**
     * Método para refrescar el token.
     * 
     * @param request
     * @param response
     */        
    @GetMapping(value = "/refresh")
    @Override
    public void refresh(HttpServletRequest request, HttpServletResponse response) {
        //TODO cambio por seguridades a keycloack
        //super.refresh(request, response);
    }
}