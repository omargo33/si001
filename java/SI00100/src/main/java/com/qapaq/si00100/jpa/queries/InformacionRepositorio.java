package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si00100.jpa.model.Informacion;

/**
 * Interfaces para CRUD de JPA Informacion.
 * 
 * @author o.velez
 * @date 2020-10-09
 * 
 */
@Repository
public interface InformacionRepositorio extends PagingAndSortingRepository<Informacion, Long> {
    /**
     * Metodo para listar informaciones paginado.
     * 
     */
    @Query("SELECT i FROM Informacion i")
    List<Informacion> findAllPage(Pageable pageable);

    /**
     * Metodo para buscar informacion por id_informacion.
     * 
     */
    @Query("SELECT i FROM Informacion i WHERE i.idInformacion = ?1")
    Informacion findByIdInformacion(Long idInformacion);

    /**
     * Metodo para buscar informacion por like nombre y paginado.
     * 
     */
    @Query("SELECT i FROM Informacion i WHERE i.nombre LIKE %?1%")
    List<Informacion> findAllByNombre(String nombre, Pageable pageable);

    /**
     * Metodo para validar si existe una informacion con el nombre y devuelve un valor
     * booleano.
     * 
     */
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Informacion i WHERE i.nombre = ?1")
    boolean existsByNombre(String nombre);

    /**
     * Metodo para validar si existe una informacion con la mismo nombre y diferente
     * idInformacion y devuelve un valor booleano.
     * 
     */
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Informacion i WHERE i.nombre = ?1 AND i.idInformacion <> ?2")
    boolean existsByNombreAndIdInformacion(String nombre, Long idInformacion);

    /**
     * Metodo para borrado por id_informacion.
     * 
     */
    @Query("DELETE FROM Informacion i WHERE i.idInformacion = ?1")
    void deleteByIdInformacion(Long idInformacion);    
}
