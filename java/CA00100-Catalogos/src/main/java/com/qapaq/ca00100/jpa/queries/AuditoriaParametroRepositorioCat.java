package com.qapaq.ca00100.jpa.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.ca00100.jpa.model.AuditoriaParametroCat;

/**
 * Interface para la clase Acceso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-12-02
 * 
 */
public interface AuditoriaParametroRepositorioCat extends JpaRepository<AuditoriaParametroCat, Long > {

    /**
     * Busca un registro por su id.
     */
    @Query("SELECT a FROM AuditoriaParametroCat a WHERE a.idAuditoriaParametro = ?1")
    AuditoriaParametroCat findByIdAuditoriaParametro(Long idAuditoriaParametro);

    /**
     * Buscar registros por idAuditoria.
     */
    @Query("SELECT a FROM AuditoriaParametroCat a WHERE a.idAuditoria = ?1")
    AuditoriaParametroCat findByIdAuditoria(Long idAuditoria);    
}
