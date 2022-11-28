package com.qapaq.gs00100.servicio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ar00100.jpa.model.Direccion;
import com.qapaq.ar00100.servicio.DireccionServicio;
import com.qapaq.gs00100.Constantes;
import com.qapaq.gs00100.jpa.model.Token;
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
    private TokenServicio tokenServicio;
    
    @Autowired
    private VGroupMembersServicio vGroupMembersServicio;
    
    @Autowired
    private DireccionServicio direccionServicio;

    @Autowired
    private HttpServletRequest request;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Token token = tokenServicio.findBySocialNickAndTipo(username, Constantes.TIPO_USER_NAME);

        if (token == null) {
            Direccion direccion = new Direccion();            
            direccion.setElemento(this.getClass().getName());
            direccion.setDireccion(request.getRemoteAddr());
            direccion.setNavegadorDispositivo(request.getHeader("User-Agent"));
            direccionServicio.saveDireccion(direccion, username, appName+" "+appVersion);
            throw new UsernameNotFoundException("E-SI00100-22");
        }

        List<VGroupMembers> listaVGroupMembers = vGroupMembersServicio.findByNombreVGroupMembers(username);
        final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();               
        for (VGroupMembers vGroupMembers : listaVGroupMembers) {
            authorities.add(new SimpleGrantedAuthority(vGroupMembers.getName()));
        }
                
        return new User(username, token.getToken(), authorities);        
    }
}
