package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si00100.jpa.model.RecursoMonitor;
import com.qapaq.si00100.jpa.queries.RecursoMonitorRepositorio;

/**
 * Clase de servicio CRUD para RecursoMonitorRepositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-16
 * 
 */
@Service
@Transactional
public class RecursoMonitorServicio {

    private final RecursoMonitorRepositorio recursoMonitorRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param recursoMonitorRepositorio
     */
    public RecursoMonitorServicio(RecursoMonitorRepositorio recursoMonitorRepositorio) {
        this.recursoMonitorRepositorio = recursoMonitorRepositorio;
    }

    /**
     * Metodo guardar recurso-monitor.
     */
    public RecursoMonitor saveRecursoMonitor(RecursoMonitor recursoMonitor, String usuario, String usuarioPrograma) {
        recursoMonitor.setEstado("A");
        recursoMonitor.setUsuario(usuario);
        recursoMonitor.setUsuarioFecha(new Date());
        recursoMonitor.setUsuarioPrograma(usuarioPrograma);
        return recursoMonitorRepositorio.save(recursoMonitor);
    }

    /**
     * Metodo para encontrar recurso-monitor por id_recurso_monitor con el estado <> 'X'
     * 
     * @param idRecursoMonitor
     */
    public RecursoMonitor findByIdRecursoMonitor(Long idRecursoMonitor) {
        return recursoMonitorRepositorio.findByIdRecursoMonitor(idRecursoMonitor);
    }
     

    /**
     * Metodo para buscar los recurso por id_monitor y el estado sea distinto a X, ademas paginado.
     */
    public List<RecursoMonitor> findAllByIdMonitor(Long idMonitor, Pageable pageable) {
        return recursoMonitorRepositorio.findAllByIdMonitor(idMonitor, pageable);
    }

    /**
     * Metodo para buscar los recurso por id_recurso y el estado sea distinto a X, ademas paginado.
     */
    public List<RecursoMonitor> findAllByIdRecurso(Long idRecurso, Pageable pageable) {
        return recursoMonitorRepositorio.findByIdRecurso(idRecurso, pageable);
    }

    /**
     * Borrado logico de un recurso-monitor por el id_recurso_monitor.
     */
    public void deleteByIdRecursoMonitor(Long idRecurso) {
        recursoMonitorRepositorio.deleteByIdRecursoMonitor(idRecurso);
    }    
}