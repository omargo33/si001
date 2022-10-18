package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si00100.jpa.model.RecursoMonitorCalendario;

/**
 * Interfaces para CRUD de JPA Recurso-Monitor-Calendario.
 * 
 * @author o.velez
 * @date 2020-10-16
 * 
 */
@Repository
public interface  RecursoMonitorCalendarioRepositorio extends PagingAndSortingRepository<RecursoMonitorCalendario, Long> {
    
    /**
     * Busqueda de Recurso-Monitor-Calendario por id_recurso_monitor_calendario.
     *
     */    
    @Query("SELECT rmc FROM RecursoMonitorCalendario rmc WHERE rmc.idRecursoMonitorCalendario = ?1")
    RecursoMonitorCalendario findByIdRecursoMonitorCalendario(Long idRecursoMonitorCalendario);

    /**
     * Metodo para buscar los recursoMonitorCalendario por id_recurso_monitor y  paginado.
     */
    @Query("SELECT rmc FROM RecursoMonitorCalendario rmc WHERE rmc.idRecursoMonitor = ?1")
    List<RecursoMonitorCalendario> findAllByIdRecursoMonitor(Long idRecursoMonitor, Pageable pageable);    

    /**
     * Metodo para buscar los recursoMonitorCalendario por id_recurso_monitor y fecha de presentacion truncada.
     */
    @Query("SELECT rmc FROM RecursoMonitorCalendario rmc WHERE rmc.idRecursoMonitor = ?1 AND rmc.fechaPresentacion = date(?2)")
    List<RecursoMonitorCalendario> findAllByIdRecursoMonitorAndFechaPresentacion(Long idRecursoMonitor, java.util.Date fechaPresentacion);
}
