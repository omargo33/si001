package com.qapaq.ca00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qapaq.ca00100.jpa.model.ModuloCat;

/**
 * Clase para la interfaz de la clase Modulo.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-10
 * 
 */
public interface ModuloRepositorioCat extends PagingAndSortingRepository<ModuloCat, Long> {

    /**
     * Metodo para buscar un objeto por id y con estado distinto a eliminado (X).
     * 
     * @param id
     * @return
     */
    @Query("SELECT m FROM ModuloCat m WHERE m.idModulo = ?1 AND m.estado <> 'X'")
    public ModuloCat findByIdModulo(Long id);

    /**
     * Metodo para buscar un objeto like indice y con estado distinto a eliminado
     * (X) y paginado.
     * 
     * @param indice
     * @return
     */
    @Query("SELECT m FROM ModuloCat m WHERE m.indice LIKE %?1% AND m.estado <> 'X'")
    public List<ModuloCat> findByIndiceAndEstadoNot(String indice, Pageable pageable);


    /**
     * Metodo para buscar todos los objetos paginados.
     * 
     * @param pageable
     * @return
     */
    @Query("SELECT m FROM ModuloCat m WHERE m.estado <> 'X'")
    public List<ModuloCat> findAllByEstadoNot(Pageable pageable);

    /**
     * Metodo para buscar un objeto por like nombre, paginado.
     * 
     * @param nombre
     * @param pageable
     * @return
     */
    @Query("SELECT m FROM ModuloCat m WHERE m.nombre LIKE %?1% AND m.estado <> 'X'")
    public List<ModuloCat> findByNombreLike(String nombre, Pageable pageable);

    /**
     * Metodo para buscar un objeto por like nombre exacto.
     * 
     * @param indice
     * @param pageable
     * @return
     */
    @Query("SELECT m FROM ModuloCat m WHERE m.indice = ?1 AND m.estado <> 'X'")
    public ModuloCat findByIndice(String indice);

    /**
     * Metodo para borrar el objeto por id en forma logica (cambio de estado a X).
     * 
     * @param id
     * 
     */    
    @Modifying
    @Query("UPDATE ModuloCat m SET m.estado = 'X' WHERE m.idModulo = ?1")
    public void deleteByIdModulo(Long id);

    /**
     * Metodo para validar si existe un modulo y devuelve el valor booleano.
     * 
     */
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM ModuloCat m WHERE m.indice = ?1 AND m.estado <> 'X'")
    public boolean existsByIndice(String indice);

    /**
     * Metodo para validar si existe un modulo y diferente idModulo y devuelve el valor booleano.
     * 
     */
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM ModuloCat m WHERE m.indice = ?1 AND m.idModulo <> ?2 AND m.estado <> 'X'")
    public boolean existsByIndiceAndIdModulo(String indice, Long idModulo);

    /**
     * Metodo para validar si existe un modulo por nombre y devuelve el valor booleano.
     * 
     */
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM ModuloCat m WHERE m.nombre = ?1 AND m.estado <> 'X'")
    public boolean existsByNombre(String nombre);

    /**
     * Metodo para validar si existe un modulo por nombre y diferente idModulo y devuelve el valor booleano.
     * 
     */
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM ModuloCat m WHERE m.nombre = ?1 AND m.idModulo <> ?2 AND m.estado <> 'X'")
    public boolean existsByNombreAndIdModulo(String nombre, Long idModulo);
}
