package com.qapaq.gs00100.servicio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qapaq.gs00100.jpa.model.VGroupMembers;

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
@Transactional("gs001001TransactionManager")
public class SeguridadServicio implements UserDetailsService {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired
    private VGroupMembersServicio vGroupMembersServicio;    
    
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;
    
    /**
     * Método para validar el usuario y roles.
     * 
     * @param username
     * @return
     * 
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        int estado = usuarioServicio.validarUsuarioLogin(userName);
        if (estado < 0) {            
            throw new UsernameNotFoundException("W-GS00100-5");
        }       

        List<VGroupMembers> listaVGroupMembers = vGroupMembersServicio.findByNombreVGroupMembers(userName);
        final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();               
        for (VGroupMembers vGroupMembers : listaVGroupMembers) {
            authorities.add(new SimpleGrantedAuthority(vGroupMembers.getName()));
        }
                
        return new User(userName, usuarioServicio.getTokenUsuario().getTokenPassword(), authorities);        
    }
}
