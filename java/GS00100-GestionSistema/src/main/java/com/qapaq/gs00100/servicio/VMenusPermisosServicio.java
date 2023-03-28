package com.qapaq.gs00100.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00100.jpa.model.VMenusPermisos;
import com.qapaq.gs00100.jpa.queries.VMenusPermisosRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Objeto para dar soporte a servicio REST de menus por permisos.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-27
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class VMenusPermisosServicio {
    
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    VMenusPermisosRepositorio vMenusPermisosRepositorio;

    /**
     * Metodo para buscar los menus y permisos por nick.
     */
    public List<VMenusPermisos> buscarPorNick(String nick) {
        return vMenusPermisosRepositorio.findAllByNick(nick);
    }
    
}
