package com.qapaq.si00100.jpa.queries;

import com.qapaq.si00100.jpa.model.Cliente;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Interfaces para busqueda de clientes.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-02
 * 
 */
public interface ClienteRepositorio extends PagingAndSortingRepository<Cliente, Long> {

    /**
     * Busqueda de clientes con estado <> 'X', paginado
     * 
      * @param pageable
      * @return
      */
    @Query("SELECT c FROM Cliente c WHERE c.estado <> 'X'")    
    List<Cliente> findAllPage(Pageable pageable);

    /**
     * Busqueda de clientes por id_cliente con el estado <> 'X'.
     * 
     * @param id
     * @return
     */
    @Query("SELECT c FROM Cliente c WHERE c.idCliente = ?1 AND c.estado <> 'X'")    
    Cliente findAllByIdCliente(Long id);

    /**
     * Busqueda de clientes por like razon social con el estado <> 'X' y ordenado por la razon social y paginado.
     * 
     * @param razonSocial
     * @return
     */
    @Query("SELECT c FROM Cliente c WHERE c.razonSocial LIKE %?1% AND c.estado <> 'X'")
    List<Cliente> findAllByRazonSocial(String razonSocial, Pageable pageable);

    /**
     * Busqueda de clientes por like identificacion con el estado <> 'X' y ordenado por la razon social y paginado.
     */
    @Query("SELECT c FROM Cliente c WHERE c.identificacion LIKE %?1% AND c.estado <> 'X'")
    List<Cliente> findAllByIdentificacion(String identificacion, Pageable pageable);

    /**
     * Metodo para borrado logico de un cliente, se pasa a estado = 'X'.
     */
    @Modifying
    @Query("UPDATE Cliente c SET c.estado = 'X' WHERE c.idCliente = ?1")
    void deleteByIdCliente(Long idCliente);

    /**
     * Metodo para validar si existe un cliente con el correo.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cliente c WHERE c.correo = ?1 AND c.estado <> 'X'")
    boolean existsByEmail(String email);

    /**
     * Metodo para validar si existe un cliente con la mismo correo y diferente idCliente.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cliente c WHERE c.correo = ?1 AND c.idCliente <> ?2 AND c.estado <> 'X'")
    boolean existsByEmailAndIdClienteNot(String email, Long idCliente);

    /**
     * Metodo para validar si existe un cliente con la identificacion.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cliente c WHERE c.identificacion = ?1 AND c.estado <> 'X'")
    boolean existsByIdentificacion(String identificacion);

    /**
     * Metodo para validar si existe un cliente con la misma identificacion y diferente idCliente.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cliente c WHERE c.identificacion = ?1 AND c.idCliente <> ?2 AND c.estado <> 'X'")
    boolean existsByIdentificacionAndIdCliente(String identificacion, Long idCliente);
    
}
