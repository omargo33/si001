package com.qapaq.ca00100.jpa.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.ca00100.jpa.model.AuditoriaEventoCat;

public interface AuditoriaEventoRepositorioCat extends JpaRepository<AuditoriaEventoCat, Long> {

    /**
     * Busca un registro por su id.
     */
    @Query("SELECT a FROM AuditoriaEventoCat a WHERE a.idAuditoriaEvento = ?1")
    AuditoriaEventoCat findByIdAuditoriaEvento(Long idAuditoriaEvento);

    /**
     * Buscar auditoria evento por id de auditoria.
     */
    @Query("SELECT a FROM AuditoriaEventoCat a WHERE a.idAuditoria = ?1")
    AuditoriaEventoCat findByIdAuditoria(Long idAuditoria);    
}
