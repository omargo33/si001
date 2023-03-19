package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si00100.jpa.model.RecursoMonitorPresentacion;

/**
 * Interfaces para CRUD de JPA Recurso-Monitor-Presentacion.
 * 
 * @author o.velez
 * @date 2020-10-16
 * 
 */
@Repository
public interface  RecursoMonitorPresentacionRepositorio extends PagingAndSortingRepository<RecursoMonitorPresentacion, Long> {

    /**
     * Busqueda de Recurso-Monitor-Presentacion por id_recurso_monitor_presentacion.
     */
    @Query("SELECT rmp FROM RecursoMonitorPresentacion rmp WHERE rmp.idRecursoMonitorPresentacion = ?1")
    RecursoMonitorPresentacion findByIdRecursoMonitorPresentacion(Long idRecursoMonitorPresentacion);

    /**
     * Metodo para buscar los recursoMonitorPresentacion por id_recurso_monitor y  paginado.
     */
    @Query("SELECT rmp FROM RecursoMonitorPresentacion rmp WHERE rmp.idRecursoMonitor = ?1")
    List<RecursoMonitorPresentacion> findAllByIdRecursoMonitor(Long idRecursoMonitor, Pageable pageable);
    
}
