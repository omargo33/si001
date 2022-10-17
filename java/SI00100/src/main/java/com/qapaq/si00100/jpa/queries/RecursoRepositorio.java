package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si00100.jpa.model.Recurso;

/**
 * Interfaces CRUD para JPA Recurso.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-14
 */
@Repository
public interface RecursoRepositorio extends PagingAndSortingRepository<Recurso, Long> {

    /**
     * Listar todos los recursos con estado <> 'X' de manera paginada.
     * 
     * @param pageable
     * @return
     */    
    @Query("SELECT r FROM Recurso r WHERE r.estado <> 'X'")
    List<Recurso> findAllRecursos(Pageable pageable);

    /**
     * Lista de recursos por id_proyecto y paginado.
     */
    @Query("SELECT r FROM Recurso r WHERE r.idProyecto = ?1")
    List<Recurso> findAllByIdProyecto(Long idProyecto, Pageable pageable);

    /**
     * Buscar un recurso por like nombre e id_proyecto, ademas paginado.
     *
     * @param nombre
     * @param idRecurso
     * @return
     */
    @Query("SELECT r FROM Recurso r WHERE r.nombre LIKE %?1% AND r.idProyecto = ?2")
    List<Recurso> findRecursoByNombreAndIdProyecto(String nombre, Long idProyecto, Pageable pageable);

    /**
     * Busqueda de Recurso por id_recurso y el estado sea distinto a X.
     */
    @Query("SELECT r FROM Recurso r WHERE r.idRecurso = ?1 AND r.estado <> 'X'")
    Recurso findByIdRecurso(Long idRecurso);

    /**
     * Borrar logico cambiando el estado a X.
     */
    @Modifying
    @Query("UPDATE Recurso r SET r.estado = 'X' WHERE r.idRecurso = ?1")
    void deleteByIdRecurso(Long idRecurso);
}