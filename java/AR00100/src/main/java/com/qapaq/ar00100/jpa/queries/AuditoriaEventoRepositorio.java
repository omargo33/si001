package com.qapaq.ar00100.jpa.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.ar00100.jpa.model.AuditoriaEvento;

public interface AuditoriaEventoRepositorio extends JpaRepository<AuditoriaEvento, Long> {

    /**
     * Busca un registro por su id.
     */
    @Query("SELECT a FROM AuditoriaEvento a WHERE a.idAuditoriaEvento = ?1")
    AuditoriaEvento findByIdAuditoriaEvento(Long idAuditoriaEvento);

    /**
     * Buscar auditoria evento por id de auditoria.
     */
    @Query("SELECT a FROM AuditoriaEvento a WHERE a.idAuditoria = ?1")
    AuditoriaEvento findByIdAuditoria(Long idAuditoria);

    /**
     * Conocer largo de la tabla para paginación.
     */
    @Query("SELECT COUNT(a) FROM AuditoriaEvento a")
    Long countAll();    
}
