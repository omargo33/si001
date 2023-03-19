package com.qapaq.ga00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qapaq.ga00100.jpa.model.Archivo;

/**
 * Interfaces para CRUD de JPA Archivo.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-11
 * 
 */
public interface ArchivoRepositorio extends PagingAndSortingRepository<Archivo, Long> {

    /**
     * Metodo para listar archivos paginado que el estado sea diferente a X.
     */
    @Query("SELECT a FROM Archivo a WHERE a.estado <> 'X'")
    List<Archivo> findAllPage(Pageable pageable);

    /**
     * Metodo para listar archivos paginado buscando por nombre like y que el estado sea diferente a X.
     */
    @Query("SELECT a FROM Archivo a WHERE a.nombre LIKE %?1% AND a.estado <> 'X'")
    List<Archivo> findAllPageByNombre(String nombre, Pageable pageable);

    /**
     * Metodo para listar archivos paginado buscando por id_grupo y que el estado sea diferente a X.
     */
    @Query("SELECT a FROM Archivo a WHERE a.idGrupo = ?1 AND a.estado <> 'X'")
    List<Archivo> findAllPageByIdGrupo(Long idGrupo, Pageable pageable);

    /**
     * Metodo para obtener un archivo por id_archivo que el estado sea distinto de X.
     */
    @Query("SELECT a FROM Archivo a WHERE a.idArchivo = ?1 AND a.estado <> 'X'")
    Archivo findByIdArchivo(Long idArchivo);

    /**
     * Metodo para obtener un archivo por nombre_ramdon que el estado sea distinto de X.
     */
    @Query("SELECT a FROM Archivo a WHERE a.nombreRamdon = ?1 AND a.estado <> 'X'")
    Archivo findByNombreRamdon(String nombreRamdon);

    /**
     * Metod para borrar un archivo por id_archivo cambiando el estado a X.
     */
    @Query("UPDATE Archivo a SET a.estado = 'X' WHERE a.idArchivo = ?1")
    void deleteByIdArchivo(Long idArchivo);
}
