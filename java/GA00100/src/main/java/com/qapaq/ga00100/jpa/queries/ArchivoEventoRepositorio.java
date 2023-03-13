package com.qapaq.ga00100.jpa.queries;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.qapaq.ga00100.jpa.model.ArchivoEvento;

/**
 * Interfaces para CRUD de JPA Archivo Evento.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-11
 * 
 */
public interface ArchivoEventoRepositorio extends PagingAndSortingRepository<ArchivoEvento, Long> {

    /**
     * Metodo para obtener un archivo evento por id_archivo_evento.
     */
    ArchivoEvento findByIdArchivoEvento(Long idArchivoEvento);

    /**
     * Metodo para obtener todos archivo_eventos por id_archivo de manera paginada.
     */
    @Query("SELECT a FROM ArchivoEvento a WHERE a.idArchivo = ?1")
    List<ArchivoEvento> findAllByIdArchivo(Long idArchivo, org.springframework.data.domain.Pageable pageable);
}
