package com.qapaq.gs00100.http.request;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.gs00100.ConstantesGS00100;
import com.qapaq.gs00100.jpa.model.VGroupMembers;
import com.qapaq.gs00100.servicio.TokenServicio;
import com.qapaq.gs00100.servicio.VGroupMembersServicio;
import com.qapaq.http.request.ComonRefreshTokenControlador;
import com.qapaq.http.request.TokenRefreshControlador;

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
public class RefreshTokenControlador extends ComonRefreshTokenControlador implements TokenRefreshControlador {

    private TokenServicio tokenServicio;
    private VGroupMembersServicio vGroupMembersServicio;

    /**
     * Constructor de la clase.
     * 
     * @param monitorServicio
     */
    @Autowired
    public RefreshTokenControlador(TokenServicio tokenServicio, VGroupMembersServicio vGroupMembersServicio) {
        this.tokenServicio = tokenServicio;
        this.vGroupMembersServicio = vGroupMembersServicio;
    }

    /**
     * Método para validar el usuario.
     * 
     * @param username
     * @return
     * 
     */
    @Override
    public boolean isActiveUser(String username) {        
        return tokenServicio.existsBySocialNickAndTipoAndIdTokenNot(username, ConstantesGS00100.TIPO_USER_NAME);
    }

    /**
     * Método para consultar los roles.
     * 
     * @param username
     * @return
     * 
     */
    @Override
    public List<String> getRoles(String username) {
        List<String> roles = new ArrayList<>();
        List<VGroupMembers> listvGroupMembers = vGroupMembersServicio.findByNombreVGroupMembers(username);
        
        for (VGroupMembers a : listvGroupMembers){
            roles.add(a.getName());
        }
        
        return roles;
    }

    /**
     * Método para refrescar el token.
     * 
     * @param request
     * @param response
     */        
    @Override
    @GetMapping(value = "/refresh")
    public void refresh(HttpServletRequest request, HttpServletResponse response) {
        super.refresh(request, response);
    }
}