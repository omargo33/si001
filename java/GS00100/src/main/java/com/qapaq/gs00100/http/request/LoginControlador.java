package com.qapaq.gs00100.http.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qapaq.ConstantesTools;
import com.qapaq.gs00100.ConstantesGS00100;
import com.qapaq.gs00100.jpa.model.VGroupMembers;
import com.qapaq.gs00100.servicio.TokenServicio;
import com.qapaq.gs00100.servicio.VGroupMembersServicio;
import com.qapaq.http.request.ComonRefreshTokenControlador;
import com.qapaq.http.request.TokenRefreshControlador;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@RequiredArgsConstructor
public class LoginControlador extends ComonRefreshTokenControlador implements TokenRefreshControlador {

    @Autowired
    private TokenServicio tokenServicio;
    @Autowired
    private VGroupMembersServicio vGroupMembersServicio;

    @Value("${spring.application.name}")
    private String appName;

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

        for (VGroupMembers a : listvGroupMembers) {
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
    @PostMapping(value = "/refresh")
    public void refresh(HttpServletRequest request, HttpServletResponse response) {
        super.refresh(request, response);
    }

    /**
     * Método para refrescar enviar password al correo.
     */
    @Override
    @PostMapping(value = "/lostPassword")
    public void lostPassword(HttpServletRequest request, HttpServletResponse response) {        
        String correo =String.valueOf(request.getParameter(ConstantesTools.EMAIL));
        String ip = request.getRemoteAddr() + " " + request.getRemoteHost() + ":" + request.getRemotePort();
        String userAgent = request.getHeader("User-Agent");

        log.warn("correo {} ip {} userAgent {}", correo, ip, userAgent);
        boolean estado = tokenServicio.enviarToken(correo, ip, userAgent, appName  );

        if (!estado) {         
            response.setStatus(HttpStatus.FORBIDDEN.value());

            Map<String, String> error = new HashMap<>();
            error.put("error_message", "W-GS00100-5");
            response.setContentType("application/json");
            try {
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            } catch (Exception ex) {
                log.error("W-GS00100-5 {}", ex.getMessage());
            }
        }else {
            response.setStatus(HttpStatus.OK.value());
        }
    }
}