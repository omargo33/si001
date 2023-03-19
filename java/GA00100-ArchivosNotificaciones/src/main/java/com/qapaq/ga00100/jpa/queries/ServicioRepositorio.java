package com.qapaq.ga00100.jpa.queries;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.qapaq.ga00100.jpa.model.Servicio;

/**
 * Interfaces para CRUD de JPA Archivo.
 * 
 * @author
import com.qapaq.ga00100.jpa.model.Servicio;

/**
 * Interfaces para CRUD de JPA Archivo.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-11
 * 
 */
public interface ServicioRepositorio extends PagingAndSortingRepository<Servicio, Long> {

    /**
     * Metodo para obtener un servicio por id_servicio.
     */
    Servicio findByIdServicio(Long idServicio);

    /**
     * Metodo para obtener un servicio por like indice, like nombre y termine paginado
     */
    @Query("SELECT a FROM Servicio a WHERE a.indice LIKE %?1% AND a.nombre LIKE %?2%")
    List<Servicio> findAllByIndiceAndNombre(String indice, String nombre, Pageable pageable);
}