package com.qapaq.ca00100.jpa.queries;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.ca00100.jpa.model.AuditoriaCat;

/**
 * Interface para la clase Acceso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-12-02
 * 
 */
public interface AuditoriaRepositorioCat extends JpaRepository<AuditoriaCat, Long > {

    /**
     * Busca un registro por su id.
     * 
     */
    @Query("SELECT a FROM AuditoriaCat a WHERE a.idAuditoria = ?1")
    AuditoriaCat findByIdAuditoria(Long idAuditoria);

    /**
     * Busca un registro en forma paginada en rangos de fechas.
     * 
     */
    @Query("SELECT a FROM AuditoriaCat a WHERE a.usuarioFecha BETWEEN ?1 AND ?2")
    List<AuditoriaCat> findByUsuarioFechaBetween(Date fechaInicio, Date fechaFin, Pageable pageable);    
}
