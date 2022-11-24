package com.qapaq.gs00100.servicio;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00100.jpa.model.Token;

/**
 * Clase de servicio Seguridad Monitor repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 * @see https://www.youtube.com/watch?v=VVn9OG9nfH0
 * @see security 
 * 
 * Esta seguridad se instancia por la configuracion del pom que busca el objeto con la implementacion de UserDetailsService
 * 
 */
@Service
@Transactional
public class SeguridadServicio implements UserDetailsService {
    private TokenServicio tokenServicio;

    @Autowired
    public SeguridadServicio(TokenServicio tokenServicio) {
        this.tokenServicio = tokenServicio;
    }

    /**
     * Método para validar el usuario y roles.
     * 
     * @param username
     * @return
     * 
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Token token = tokenServicio.findBySocialNickAndTipo(username, "C");

        if (token == null) {
            throw new UsernameNotFoundException("E-SI00100-22");
        }


        Collection<SimpleGrantedAuthority> authorities = new ArrayList();        
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(username, token.getToken(), authorities);        
    }
}
