package com.qapaq.si00100.servicio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qapaq.si00100.jpa.model.VRecursoMonitor;
import com.qapaq.si00100.jpa.queries.VRecursoMonitorRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de v recurso monitor.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-02
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class VRecursoMonitorServicio {

    @Autowired
    private final VRecursoMonitorRepositorio vRecursoMonitorRepositorio;

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
