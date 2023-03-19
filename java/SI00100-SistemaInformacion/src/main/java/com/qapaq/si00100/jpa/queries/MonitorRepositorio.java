package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si00100.jpa.model.Monitor;

/**
 * Interfaces para CRUD de JPA Monitor.
 * 
 * @author o.velez
 * @date 2020-10-09
 * 
 */
@Repository
public interface MonitorRepositorio extends PagingAndSortingRepository<Monitor, Long> {
    
        /**
        * Metodo para listar informaciones paginado.
        * 
        */
        @Query("SELECT m FROM Monitor m")
        List<Monitor> findAllPage(Pageable pageable);
    
        /**
        * Metodo para buscar informacion por id_informacion.
        * 
        */
        @Query("SELECT m FROM Monitor m WHERE m.idMonitor = ?1")
        Monitor findByIdMonitor(Long idMonitor);
    
        /**
        * Metodo para buscar informacion por like nombre y paginado.
        * 
        */
        @Query("SELECT m FROM Monitor m WHERE m.nombre LIKE %?1%")
        List<Monitor> findAllByNombre(String nombre, Pageable pageable);

        /**
         * Metodo para buscar monitor por mac address en la wifi o en la network.
         * 
         * @param wifi
         * @param network
         * @return
         */
        @Query("SELECT m FROM Monitor m WHERE m.wifi = ?1 OR m.network = ?2")
        Monitor findByMacAddress(String wifi, String network);
    
        /**
        * Metodo para validar si existe una informacion con el nombre y devuelve un valor
        * booleano.
        * 
        */
        @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Monitor m WHERE m.nombre = ?1")
        boolean existsByNombre(String nombre);
    
        /**
        * Metodo para validar si existe una informacion con la mismo nombre y diferente
        * idMonitor y devuelve un valor booleano.
        * 
        */
        @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Monitor m WHERE m.nombre = ?1 AND m.idMonitor <> ?2")
        boolean existsByNombreAndIdMonitor(String nombre, Long idMonitor);
    
        /**
        * Metodo para validar si existe una informacion con la misma serie y devuelve un
        * valor booleano.
        * 
        */
        @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Monitor m WHERE m.serie = ?1")
        boolean existsBySerie(String serie);
    
        /**
        * Metodo para validar si existe una informacion con la misma serie y diferente
        * idMonitor y devuelve un valor booleano.
        * 
        */
        @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Monitor m WHERE m.serie = ?1 AND m.idMonitor <> ?2")        
        boolean existsBySerieAndIdMonitor(String serie, Long idMonitor);
}
