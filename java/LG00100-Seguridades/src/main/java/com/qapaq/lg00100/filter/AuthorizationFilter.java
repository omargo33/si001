package com.qapaq.lg00100.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qapaq.ConstantesTools;

/**
 * Clase para personalizar el filtro de autorización.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-23
 * 
 * @see https://www.youtube.com/watch?v=VVn9OG9nfH0
 * @see security
 */
public class AuthorizationFilter extends OncePerRequestFilter {

    private String contexto;

    /**
     * Constructor de la clase.
     * 
     * @param contexto
     */
    public AuthorizationFilter(String contexto) {
        this.contexto = contexto;
    }

    /**
     * Método para autorizar al usuario.
     * 
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
        if (request.getServletPath().equals(contexto + "/login") || 
            request.getServletPath().equals("/login/refresh")){                
            filterChain.doFilter(request, response);    
        }else{
            String authorizationHeader = request.getHeader(ConstantesTools.HEADER_STRING);
            if (authorizationHeader != null && authorizationHeader.startsWith(ConstantesTools.TOKEN_PREFIX)){
                try {
                    String token = authorizationHeader.substring(ConstantesTools.TOKEN_PREFIX.length());
                    Algorithm algorithm = Algorithm.HMAC256(ConstantesTools.getPassword());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim(ConstantesTools.ROLES_STRING).asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    for (String role : roles) {
                        authorities.add(new SimpleGrantedAuthority(role));
                    }
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {                    
                    response.setHeader("error", e.getMessage());
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error", e.getMessage());
                    response.setContentType("application/json");
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }else{
                filterChain.doFilter(request, response);
            }
        }
    }
}