package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qapaq.si00100.jpa.model.Localizacion;

/**
 * Interfaces para CRUD de JPA Localizacion.
 * 
 * @author o.velez
 * @date 2020-10-09
 * 
 */
public interface LocalizacionRepositorio extends PagingAndSortingRepository<Localizacion, Long> {

    /**
     * Metodo para listar informaciones paginado.
     * 
     */
    @Query("SELECT l FROM Localizacion l")
    List<Localizacion> findAllPage(Pageable pageable);

    /**
     * Metodo para buscar informacion por id_informacion.
     * 
     */
    @Query("SELECT l FROM Localizacion l WHERE l.idLocalizacion = ?1")
    Localizacion findByIdLocalizacion(Long idLocalizacion);

    /**
     * Metodo para buscar informacion por like nombre y paginado.
     * 
     */
    @Query("SELECT l FROM Localizacion l WHERE l.nombre LIKE %?1%")
    List<Localizacion> findAllByNombre(String nombre, Pageable pageable);

    /**
     * Metodo para validar si existe una informacion con el nombre y devuelve un valor
     * booleano.
     * 
     */
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Localizacion l WHERE l.nombre = ?1")
    boolean existsByNombre(String nombre);

    /**
     * Metodo para validar si existe una informacion con la mismo nombre y diferente
     * idLocalizacion y devuelve un valor booleano.
     * 
     */
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Localizacion l WHERE l.nombre = ?1 AND l.idLocalizacion <> ?2")
    boolean existsByNombreAndIdLocalizacion(String nombre, Long idLocalizacion);

    /**
     * Metodo para borrado por id_informacion.
     * 
     */
    @Modifying
    @Query("DELETE FROM Localizacion l WHERE l.idLocalizacion = ?1")
    void deleteByIdLocalizacion(Long idLocalizacion);
}