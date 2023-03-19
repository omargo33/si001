package com.qapaq.ga00100.jpa.queries;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.qapaq.ga00100.jpa.model.Formato;

/**
 * Interfaces para CRUD de JPA Formato.
 */
public interface FormatoRepositorio extends PagingAndSortingRepository<Formato, Long> {

    /**
     * Metodo para obtener un formato por id_formato.
     */
    Formato findByIdFormato(Long idFormato);

    /**
     * Metodo para obtener los formatos buscados por like indice y like nombre y  este paginado
     */
    @Query("SELECT f FROM Formato f WHERE f.indice LIKE %?1% AND f.nombre LIKE %?2%")
    List<Formato> findAllPage(String indice, String nombre, Pageable pageable);
}
