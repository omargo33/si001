package com.qapaq.ar00100.jpa.queries;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.qapaq.ar00100.jpa.model.Acceso;

/**
 * Interface para la clase Acceso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-11-25
 * 
 */
@Repository
public interface AccesoRepositorio extends JpaRepository<Acceso, Long > {

    /**
     * Busca un registro por su id.
     * 
     */
    @Query("SELECT a FROM Acceso a WHERE a.idAcceso = ?1")
    Acceso findByIdAcceso(Long idAcceso);

    /**
     * Busca un registro en forma paginada en rangos de fechas.
     * 
     */
    @Query("SELECT a FROM Acceso a WHERE a.usuarioFecha BETWEEN ?1 AND ?2")
    List<Acceso> findByUsuarioFechaBetween(Date fechaInicio, Date fechaFin, Pageable pageable);

    /**
     * Insertado directo en la base de datos.
     * 
     */
    @Query(value = "INSERT INTO AR_001_00.acceso (nombre, token, token_api, usuario_fecha, usuario_programa) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    Acceso insertAcceso(String nombre, String token, String tokenApi, Date usuarioFecha, String usuarioPrograma);

     /**
     * Conocer largo de la tabla para paginación.
     * 
     */
    @Query("SELECT COUNT(a) FROM Acceso a")    
    Long countAll();
}
