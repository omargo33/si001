package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si00100.jpa.model.RecursoMonitorCalendario;
import com.qapaq.si00100.jpa.queries.RecursoMonitorCalendarioRepositorio;

/**
 * Clase de servicio CRUD para RecursoMonitorCalendarioRepositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-16
 * 
 */
@Service
@Transactional
public class RecursoMonitorCalendarioServicio {

    private RecursoMonitorCalendarioRepositorio recursoMonitorCalendarioRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param recursoMonitorCalendarioRepositorio
     */
    public RecursoMonitorCalendarioServicio(RecursoMonitorCalendarioRepositorio recursoMonitorCalendarioRepositorio) {
        this.recursoMonitorCalendarioRepositorio = recursoMonitorCalendarioRepositorio;
    }

    /**
     * Metodo para encontrar recurso-monitor-calendario por
     * id_recurso_monitor_calendario.
     * 
     * @param idRecursoMonitorCalendario
     */
    public RecursoMonitorCalendario findByIdRecursoMonitorCalendario(Long idRecursoMonitorCalendario) {
        return recursoMonitorCalendarioRepositorio.findByIdRecursoMonitorCalendario(idRecursoMonitorCalendario);
    }

    /**
     * Metodo para encontrar recurso-monitor-calendario por id_monitor_recurso.
     * 
     * @param idRecursoMonitorCalendario
     * @param pageable
     * @return
     */
    public List<RecursoMonitorCalendario> findByIdRecursoMonitorCalendario(Long idRecursoMonitorCalendario,
            Pageable pageable) {
        return recursoMonitorCalendarioRepositorio.findAllByIdRecursoMonitor(idRecursoMonitorCalendario, pageable);
    }

    /**
     * Metodo para guardar un recurso-monitor-calendario.
     * 
     * Si se quiere crear un registro para la misma recurso-monitor devuelve el existente.
     * 
     * Si se quiere cambiar la fecha para un registro existente, se mantiene el id de recurso-monitor
     *      
     */
    public RecursoMonitorCalendario save(RecursoMonitorCalendario recursoMonitorCalendario, String usuario,
            String usuarioPrograma) {
        
        if (recursoMonitorCalendario.getIdRecursoMonitorCalendario() == null) {
            List<RecursoMonitorCalendario> recursoMonitorCalendarioList = recursoMonitorCalendarioRepositorio
                    .findAllByIdRecursoMonitorAndFechaPresentacion(recursoMonitorCalendario.getIdRecursoMonitor(),
                            recursoMonitorCalendario.getFechaPresentacion());
            if (!recursoMonitorCalendarioList.isEmpty()) {
                return recursoMonitorCalendarioList.get(0);
            }
        }else{
            RecursoMonitorCalendario recursoMonitorCalendarioTemp = recursoMonitorCalendarioRepositorio.findByIdRecursoMonitorCalendario(recursoMonitorCalendario.getIdRecursoMonitorCalendario());
            recursoMonitorCalendario.setIdRecursoMonitor(recursoMonitorCalendarioTemp.getIdRecursoMonitor());
        }

        recursoMonitorCalendario.setUsuario(usuario);
        recursoMonitorCalendario.setUsuarioFecha(new Date());
        recursoMonitorCalendario.setUsuarioPrograma(usuarioPrograma);
        return recursoMonitorCalendarioRepositorio.save(recursoMonitorCalendario);
    }

    /**
     * Metodo para borrar un recurso-monitor-calendario.
     * 
     * @param idRecursoMonitorCalendario
     */
    public void deleteByIdRecursoMonitorCalendario(Long idRecursoMonitorCalendario) {
        recursoMonitorCalendarioRepositorio.deleteById(idRecursoMonitorCalendario);
    }
}
