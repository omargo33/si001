package com.qapaq.gs00100.jpa.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.TokenServidor;

/**
 * Repositorio para la entidad TokenServidor.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-11
 * 
 */
@Repository
public interface TokenServidorRepositorio extends JpaRepository<TokenServidor, Long> {
    /**
     * Metodo para buscar un objeto por id y con estado distinto a eliminado (X).
     * 
     *
     */ 
    public TokenServidor findByIdToken(Long id);

    /**
     * Metodo para buscar un objeto por id_token_servidor.
     * 
     */
    public TokenServidor findByIdTokenServidor(Long idTokenServidor);

    /**
     * Metodo para buscar un objeto por un rango de fechas en el campo usuario_fecha, tomando solo el dia.
     * 
     */
    @Query("SELECT t FROM TokenServidor t WHERE t.usuarioFecha >= ?1 AND t.usuarioFecha <= ?2")
    public TokenServidor findByUsuarioFechaBetween(String fechaInicio, String fechaFin);
}  
