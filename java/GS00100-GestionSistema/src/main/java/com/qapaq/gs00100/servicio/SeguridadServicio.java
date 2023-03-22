package com.qapaq.gs00100.servicio;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SeguridadServicio implements UserDetailsService {

    /**
     * Método para validar el usuario y roles.
     * 
     * @param username
     * @return
     * 
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        boolean estado = true;

        if (estado) {
            log.info(userName);
            return null;
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
