package com.qapaq.gs00100.http.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.gs00100.jpa.model.VMenusPermisos;
import com.qapaq.gs00100.servicio.VMenusPermisosServicio;
import com.qapaq.http.request.ComonControlador;

/**
 * Objeto para dar soporte a servicio REST de Menu
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-11
 * 
 */
@RestController
@RequestMapping(value = "/menuspermisos")
public class VMenusPermisosControlador extends ComonControlador {

    @Autowired
    VMenusPermisosServicio vMenusPermisosService;

    /**
     * Metodo para buscar lista de Menus por nick.
     *
     * @param nick
     * @return
     */
    @GetMapping("/")
    public List<VMenusPermisos> getVMenusPermisosByNick(HttpServletRequest request) {
        return vMenusPermisosService.buscarPorNick(evaluarUsuario(request));
    }
}
