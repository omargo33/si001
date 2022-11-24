package com.qapaq.si00100.servicio;

import java.util.ArrayList;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.qapaq.si00100.jpa.model.Monitor;
import com.qapaq.si00100.jpa.queries.MonitorRepositorio;

/**
 * Clase de servicio Seguridad Monitor repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 * @see https://www.youtube.com/watch?v=VVn9OG9nfH0
 * @see security 
 * 
 */
@Service
@Transactional
public class SeguridadServicio implements UserDetailsService{
    private MonitorRepositorio monitorRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param monitorRepositorio
     */
    @Autowired
    public SeguridadServicio(MonitorRepositorio monitorRepositorio) {
        this.monitorRepositorio = monitorRepositorio;
    }

    /**
     * Método para cargar el usuario.
     * 
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String monitorMacAddres) throws UsernameNotFoundException {
        Monitor monitor = this.monitorRepositorio.findByMacAddress(monitorMacAddres,monitorMacAddres);

        if (monitor == null) {   
            throw new UsernameNotFoundException("E-SI00100-22");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();        
        authorities.add(new SimpleGrantedAuthority("ROLE_MONITOR"));
        return new User(monitor.getNombre(), monitor.getClave(),authorities);
    }
}
