package com.qapaq.ar00100.jpa.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qapaq.ar00100.jpa.model.AuditoriaParametro;

/**
 * Interface para la clase Acceso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-12-02
 * 
 */
@Repository
public interface AuditoriaParametroRepositorio extends JpaRepository<AuditoriaParametro, Long > {

    /**
     * Busca un registro por su id.
     */
    @Query("SELECT a FROM AuditoriaParametro a WHERE a.idAuditoriaParametro = ?1")
    AuditoriaParametro findByIdAuditoriaParametro(Long idAuditoriaParametro);

    /**
     * Buscar registros por idAuditoria.
     */
    @Query("SELECT a FROM AuditoriaParametro a WHERE a.idAuditoria = ?1")
    AuditoriaParametro findByIdAuditoria(Long idAuditoria);

    /**
     * Conocer largo de la tabla para paginación.
     */
    @Query("SELECT COUNT(a) FROM AuditoriaParametro a")
    Long countAll();
}
