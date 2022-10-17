package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si00100.jpa.model.RecursoMonitor;

/**
 * Interfaces para CRUD de JPA Recurso-Monitor.
 * 
 * @author o.velez
 * @date 2020-10-16
 * 
 */
@Repository
public interface RecursoMonitorRepositorio extends PagingAndSortingRepository<RecursoMonitor, Long> {

    /**
     * Busqueda de Recurso-Monitor por id_recurso_monitor que el estado sea distinto a X.
     * 
     */
    @Query("SELECT rm FROM RecursoMonitor rm WHERE rm.idRecursoMonitor = ?1 AND rm.estado <> 'X'")
    RecursoMonitor findByIdRecursoMonitor(Long idRecursoMonitor); 

    /**
     * Metodo para buscar los recursoMonitor por id_monitor y el estado sea distinto a X, ademas paginado.
     */
    @Query("SELECT rm FROM RecursoMonitor rm WHERE rm.idMonitor = ?1 AND rm.estado <> 'X'")
    List<RecursoMonitor> findAllByIdMonitor(Long idMonitor, Pageable pageable);    

    /**
     * Metodo para buscar los recurso por id_recurso y el estado sea distinto a X, ademas paginado.
     */
    @Query("SELECT rm FROM RecursoMonitor rm WHERE rm.idRecurso = ?1 AND rm.estado <> 'X'")
    List <RecursoMonitor> findByIdRecurso(Long idRecurso, Pageable pageable);


    /**
     * Borrado logico de un recursoMonitor por el id_recurso_monitor.
     */
    @Modifying
    @Query("UPDATE RecursoMonitor rm SET rm.estado = 'X' WHERE rm.idRecursoMonitor = ?1")
    void deleteByIdRecursoMonitor(Long idRecurso);    
}
