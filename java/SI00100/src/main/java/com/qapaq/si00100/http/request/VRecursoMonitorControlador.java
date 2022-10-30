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

/**
 * Clase para controlar las peticiones de las ciudades.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-24
 * 
 */
@RestController
@RequestMapping(value = "/v_recursos_monitores")
public class VRecursoMonitorControlador extends ComonControlador {

    private VRecursoMonitorServicio vRecursoMonitorServicio;

    /**
     * Constructor de la clase.
     * 
     * @param vRecursoMonitorServicio
     */
    @Autowired
    public VRecursoMonitorControlador(VRecursoMonitorServicio vRecursoMonitorServicio) {
        this.vRecursoMonitorServicio = vRecursoMonitorServicio;
    }

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
