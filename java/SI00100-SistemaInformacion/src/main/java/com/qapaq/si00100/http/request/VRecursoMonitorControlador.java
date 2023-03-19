package com.qapaq.si00100.http.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.http.request.ComonControlador;
import com.qapaq.si00100.jpa.model.VRecursoMonitor;
import com.qapaq.si00100.servicio.VRecursoMonitorServicio;

import lombok.RequiredArgsConstructor;

/**
 * Clase para controlar las peticiones de las ciudades.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-24
 * 
 */
@RestController
@RequestMapping(value = "/v_recursos_monitores")
@RequiredArgsConstructor
public class VRecursoMonitorControlador extends ComonControlador {
    @Autowired
    private VRecursoMonitorServicio vRecursoMonitorServicio;


    /**
     * Metodo para obtener todos los recursos monitores buscando por nombre desde el token.
     * 
     * @return
     */
    @GetMapping("/")
    public List<VRecursoMonitor> getAllVRecursoMonitorByNetwork(HttpServletRequest request) {
        return vRecursoMonitorServicio.findAllByNombre(evaluarUsuario(request));
    }    
}
