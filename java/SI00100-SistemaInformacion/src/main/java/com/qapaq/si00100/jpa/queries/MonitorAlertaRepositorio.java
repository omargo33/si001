package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qapaq.si00100.jpa.model.MonitorAlerta;

/**
 * Interfaces para CRUD de JPA MonitorAlerta.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 */
public interface MonitorAlertaRepositorio extends PagingAndSortingRepository<MonitorAlerta, Long> {

        /**
         * Metodo para listar todos los monitores alerta de manera paginada.
         * 
         * @param idMonitorAlerta
         * @return
         */
        @Query("SELECT ma FROM MonitorAlerta ma")
        List<MonitorAlerta> findAllPage(Pageable pageable);

        /**
        * Metodo para buscar informacion por id_informacion.
        * 
        */
        @Query("SELECT m FROM MonitorAlerta m WHERE m.idMonitorAlerta = ?1")
        MonitorAlerta findByIdMonitorAlerta(Long idMonitorAlerta);
    
        /**
        * Metodo para buscar informacion por like nombre y paginado.
        * 
        */
        @Query("SELECT m FROM MonitorAlerta m WHERE m.nombre LIKE %?1%")
        List<MonitorAlerta> findAllByNombre(String nombre, Pageable pageable);        
}       
