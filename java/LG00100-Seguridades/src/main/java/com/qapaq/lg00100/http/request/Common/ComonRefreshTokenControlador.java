package com.qapaq.lg00100.http.request.Common;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qapaq.ConstantesTools;
import com.qapaq.jpa.exception.SeguridadException;

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
@Slf4j
public class ComonRefreshTokenControlador {

    /**
     * Método conocer al usuario.
     *  
      * @param username
      * @return
      */
    public boolean isActiveUser(String username) {        
        return true;
    }

    /**
     * Método recargar los roles del usuairo.
     * 
     * @param username
     * @return
     */
    public List<String> getRoles(String username) {
        List<String> roles = new ArrayList<>();        
        return roles;
    }

    /**
     * Método para recuperar la contraseña.
     * 
     * @param request
     * @param response
     */
    public void lostPassword(HttpServletRequest request, HttpServletResponse response) {
        log.error(request.getRequestURL().toString());
    }

    /**
     * Método para refrescar el token.
     * 
     * @param request
     * @param response
     */
    public void refresh(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(ConstantesTools.HEADER_STRING);
        if (authorizationHeader != null && authorizationHeader.startsWith(ConstantesTools.TOKEN_PREFIX)) {
            try {
                String token = authorizationHeader.substring(ConstantesTools.TOKEN_PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(ConstantesTools.getPassword());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String nombre = decodedJWT.getSubject();
                
                if(!isActiveUser(nombre)){
                    throw new SeguridadException("E-SI00100-19");
                }

                List<String> authorities = getRoles(nombre);

                String accessToken = JWT.create()
                        .withSubject(nombre)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim(ConstantesTools.ROLES_STRING, authorities)
                        .sign(algorithm);

                String refreshToken = JWT.create()
                        .withSubject(nombre)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put(ConstantesTools.ACCESS_TOKEN, accessToken);
                tokens.put(ConstantesTools.REFRESH_TOKEN, refreshToken);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error", e.getMessage());
                response.setContentType("application/json");
                try{
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }catch(Exception ex){
                    log.error("E-SI00100-20 {}", ex.getMessage());
                }
            }
        } else {
            throw new SeguridadException("E-SI00100-21");
        }
    }
}