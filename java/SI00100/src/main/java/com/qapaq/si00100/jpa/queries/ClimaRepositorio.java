package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.si00100.jpa.model.Clima;

/**
 * Interfaces para busqueda de Clima.
 * 
 * @author o.velez
 * @date 2020-10-05
 */
@Repository
public interface ClimaRepositorio extends PagingAndSortingRepository<Clima, Long> {

    /**
     * Metodo para buscar registros de clima por id_ciudad y con un limite parametrizado.
     */
    @Query("SELECT c FROM Clima c WHERE c.idCiudad = ?1 ORDER BY c.usuarioFecha DESC limit ?2")
    Clima findTopByIdCiudad(Long idCiudad, int limite);

    /**
     * Busquea de clima paginado.
     */
    @Query("SELECT c FROM Clima c")
    List<Clima> findAllPage(Pageable pageable);

    /**
     * Busqueda de clima por id_clima.
     */
    @Query("SELECT c FROM Clima c WHERE c.idClima = ?1")
    Clima findAllByIdClima(Long id);

    /**
     * Busqueda de clima por like nombre, paginado; y, ordenado por nombre.
     */
    @Query("SELECT c FROM Clima c WHERE c.nombre LIKE %?1% ORDER BY c.nombre")
    List<Clima> findAllByNombre(String nombre, Pageable pageable);

}