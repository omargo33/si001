package com.qapaq.ar00100.jpa.queries;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.ar00100.jpa.model.Informacion;

/**
 * Interface para la clase Informacion.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-12-02
 * 
 */
public interface InformacionRepositorio extends JpaRepository<Informacion, Long > {

    /**
     * Busca un registro por su id.
     */
    @Query("SELECT i FROM Informacion i WHERE i.idInformacion = ?1")
    Informacion findByIdInformacion(Long idInformacion);

    /**
     * Busca un registros en rango de fechas y paginado.
     */
    @Query("SELECT i FROM Informacion i WHERE i.fecha BETWEEN ?1 AND ?2")
    List<Informacion> findByFechaBetween(Date fechaInicio, Date fechaFin, Pageable pageable);

    /**
     * Conocer largo de la tabla para paginación.
     */
    @Query("SELECT COUNT(i) FROM Informacion i")
    Long countAll();    
}
