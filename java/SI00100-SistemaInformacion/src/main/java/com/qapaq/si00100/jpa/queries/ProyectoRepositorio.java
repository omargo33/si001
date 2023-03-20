package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qapaq.si00100.jpa.model.Proyecto;

/**
 * Interfaces para busqueda de Proyecto.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-14
 */
public interface ProyectoRepositorio extends PagingAndSortingRepository<Proyecto, Long> {
    
    /**
     * Listar proyectos de forma paginada.
     * 
     * @param pageable
     * @return
     */
    @Query("SELECT p FROM Proyecto p")
    List<Proyecto> findAllProyectos(Pageable pageable);

    /**
     * Busqueda de Proyecto por id_proyecto y el estado sea distinto a X.
     */
    @Query("SELECT p FROM Proyecto p WHERE p.idProyecto = ?1 AND p.estado <> 'X'")
    Proyecto findByIdProyecto(Long idProyecto);    

    /**
     * Busqueda de Proyecto por like nombre, paginado y el estado sea distinto a X.
     */
    @Query("SELECT p FROM Proyecto p WHERE p.nombre LIKE %?1% AND p.estado <> 'X'")    
    List<Proyecto> findAllByNombre(String nombre, Pageable pageable);

    /**
     * Borrado logico cambianado el estado a X.
     */
    @Modifying
    @Query("UPDATE Proyecto p SET p.estado = 'X' WHERE p.idProyecto = ?1")
    void deleteByIdProyecto(Long idProyecto);
}
