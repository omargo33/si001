package com.qapaq.si00100.servicio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qapaq.si00100.jpa.model.VRecursoMonitor;
import com.qapaq.si00100.jpa.queries.VRecursoMonitorRepositorio;

/**
 * Servicio de v recurso monitor.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-02
 * 
 */
@Service
@Transactional
public class VRecursoMonitorServicio {

    private final VRecursoMonitorRepositorio vRecursoMonitorRepositorio;

    @Autowired
    public VRecursoMonitorServicio(VRecursoMonitorRepositorio vRecursoMonitorRepositorio) {
        this.vRecursoMonitorRepositorio = vRecursoMonitorRepositorio;
    }
    
    /**
     * Metodo para buscar todos los v_recursos_monitores por nombre.
     * 
     * @param nombre
     * @return
     */
    public List<VRecursoMonitor> findAllByNombre(String nombre) {
        return vRecursoMonitorRepositorio.findAllByNombre(nombre);
    }    
}
