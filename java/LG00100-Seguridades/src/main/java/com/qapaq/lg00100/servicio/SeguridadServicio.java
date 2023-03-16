package com.qapaq.lg00100.servicio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.lg00100.ConstantesLG00100;
import com.qapaq.lg00100.jpa.model.VGroupMembers;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de servicio Seguridad Monitor repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 * @see https://www.youtube.com/watch?v=VVn9OG9nfH0
 * @see security
 * 
 *      Esta seguridad se instancia por la configuracion del pom que busca el
 *      objeto con la implementacion de UserDetailsService
 * 
 */
@Slf4j
@Service
@Transactional
public class SeguridadServicio implements UserDetailsService {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private VGroupMembersServicio vGroupMembersServicio;

    /**
     * Método para validar el usuario y roles.
     * 
     * @param username
     * @return
     * 
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        String estado = usuarioServicio.validarUsuarioLogin(userName);
        if (estado.compareTo(ConstantesLG00100.TOKEN_ESTADO_ACTIVO) == 0
                || estado.compareTo(ConstantesLG00100.TOKEN_ESTADO_CREADO) == 0) {
            List<VGroupMembers> listaVGroupMembers = vGroupMembersServicio.findByNombreVGroupMembers(userName);
            final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (VGroupMembers vGroupMembers : listaVGroupMembers) {
                log.warn(vGroupMembers.toString());
                authorities.add(new SimpleGrantedAuthority(vGroupMembers.getName()));
            }
            return new User(userName, usuarioServicio.getTokenUsuario().getTokenPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}