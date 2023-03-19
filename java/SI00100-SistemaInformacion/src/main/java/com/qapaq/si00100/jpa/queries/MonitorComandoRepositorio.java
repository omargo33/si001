package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si00100.jpa.model.MonitorComando;

/**
 * Interfaces para CRUD de JPA MonitorComando.
 * 
 * @author o.velez
 * @date 2020-10-14
 * 
 */
@Repository
public interface MonitorComandoRepositorio extends PagingAndSortingRepository<MonitorComando, Long> {
        
        /**
         * Metodo para buscar un monitor comando paginado.
         */
        @Query("SELECT mc FROM MonitorComando mc")
        List<MonitorComando> findAllPage(Pageable pageable);
    
        /**
         * Metodo para buscar monitor_comando por id_monitor_comando.
         */
        @Query("SELECT m FROM MonitorComando m WHERE m.idMonitorComando = ?1")
        MonitorComando findByIdMonitorComando(Long idMonitorComando);

        /**
         * Metodo para buscar monitor_comando por elemento y paginado.
         */
        @Query("SELECT m FROM MonitorComando m WHERE m.elemento = ?1")
        List<MonitorComando> findByElemento(String elemento, Pageable pageable);
        
    }