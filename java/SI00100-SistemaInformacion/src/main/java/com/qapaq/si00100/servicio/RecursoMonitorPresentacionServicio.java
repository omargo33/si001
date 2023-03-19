package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si00100.jpa.model.RecursoMonitorPresentacion;
import com.qapaq.si00100.jpa.queries.RecursoMonitorPresentacionRepositorio;

/**
 * Clase de servicio CRUD para RecursoMonitorPresentacionRepositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-18
 * 
 */
@Service
@Transactional
public class RecursoMonitorPresentacionServicio {

    private RecursoMonitorPresentacionRepositorio recursoMonitorPresentacionRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param recursoMonitorPresentacionRepositorio
     */
    public RecursoMonitorPresentacionServicio(
            RecursoMonitorPresentacionRepositorio recursoMonitorPresentacionRepositorio) {
        this.recursoMonitorPresentacionRepositorio = recursoMonitorPresentacionRepositorio;
    }

    /**
     * Metodo para encontrar recurso-monitor-presentacion por
     * id_recurso_monitor_presentacion.
     * 
     * @param idRecursoMonitorPresentacion
     */
    public RecursoMonitorPresentacion findByIdRecursoMonitorPresentacion(Long idRecursoMonitorPresentacion) {
        return recursoMonitorPresentacionRepositorio.findByIdRecursoMonitorPresentacion(idRecursoMonitorPresentacion);
    }

    /**
     * Metodo para encontrar recurso-monitor-presentacion por id_monitor_recurso y
     * paginado.
     * 
     * @param idRecursoMonitorPresentacion
     * @param pageable
     * @return
     */
    public List<RecursoMonitorPresentacion> findByIdRecursoMonitorPresentacion(
            Long idRecursoMonitorPresentacion,
            Pageable pageable) {
        return recursoMonitorPresentacionRepositorio.findAllByIdRecursoMonitor(idRecursoMonitorPresentacion, pageable);
    }

    /**
     * Metodo para guardar un recurso-monitor-presentacion.
     */
    public RecursoMonitorPresentacion save(RecursoMonitorPresentacion recursoMonitorPresentacion, String usuario, String usuarioPrograma) {
        recursoMonitorPresentacion.setUsuario(usuario);
        recursoMonitorPresentacion.setUsuarioFecha(new Date());
        recursoMonitorPresentacion.setUsuarioPrograma(usuarioPrograma);
        return recursoMonitorPresentacionRepositorio.save(recursoMonitorPresentacion);
    }

}
