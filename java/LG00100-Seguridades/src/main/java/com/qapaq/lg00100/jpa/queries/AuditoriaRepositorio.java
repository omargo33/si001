package com.qapaq.lg00100.jpa.queries;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qapaq.lg00100.jpa.model.Auditoria;

/**
 * Interface para la clase Acceso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-12-02
 * 
 */
@Repository
public interface AuditoriaRepositorio extends JpaRepository<Auditoria, Long > {

    /**
     * Busca un registro por su id.
     * 
     */
    @Query("SELECT a FROM Auditoria a WHERE a.idAuditoria = ?1")
    Auditoria findByIdAuditoria(Long idAuditoria);

    /**
     * Busca un registro en forma paginada en rangos de fechas.
     * 
     */
    @Query("SELECT a FROM Auditoria a WHERE a.usuarioFecha BETWEEN ?1 AND ?2")
    List<Auditoria> findByUsuarioFechaBetween(Date fechaInicio, Date fechaFin, Pageable pageable);    
}
