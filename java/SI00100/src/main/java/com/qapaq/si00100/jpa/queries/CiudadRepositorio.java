package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.qapaq.si00100.jpa.model.Ciudad;

/**
 * Interfaces para busqueda de ciudades.
 * 
 * @author o.velez
 * @date 2020-10-05
 */
@Repository
public interface CiudadRepositorio extends PagingAndSortingRepository<Ciudad, Long> {

    /**
     * Busqueda de ciudades, paginado
     */
    @Query("SELECT c FROM Ciudad c")
    List<Ciudad> findAllPage(Pageable pageable);

    /**
     * Busqueda de ciudades por id_ciudad.
     */
    @Query("SELECT c FROM Ciudad c WHERE c.idCiudad = ?1")
    Ciudad findByIdCiudad(Long id);

    /**
     * Busqueda de ciudades por like nombre y paginado.
     */
    @Query("SELECT c FROM Ciudad c WHERE c.nombre LIKE %?1%")
    List<Ciudad> findAllByNombre(String nombre, Pageable pageable);

    /**
     * Metodo para validar si existe una ciudad con el nombre y devuelve un valor booleano
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Ciudad c WHERE c.nombre = ?1")
    boolean existsByNombre(String nombre);

    /**
     * Metodo para validar si existe una ciudad con la mismo nombre y diferente idCiudad y devuelve un valor booleano.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Ciudad c WHERE c.nombre = ?1 AND c.idCiudad <> ?2")    
    boolean existsByNombreAndIdCiudad(String nombre, Long idCiudad);

    /**
     * Metodo para borrado por id_ciudad.
     */
    @Query("DELETE FROM Ciudad c WHERE c.idCiudad = ?1")
    void deleteByIdCiudad(Long idCiudad);
    
}
