package com.qapaq.ca00100.jpa.queries;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.ca00100.jpa.model.DireccionCat;

/**
 * Interface para la clase Direccion.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-11-25
 * 
 */
public interface DireccionRepositorioCat extends JpaRepository<DireccionCat, Long> {

    /**
     * Busca un registro por su id.
     */
    DireccionCat findByIdDireccion(Long idDireccion);

    /**
     * Busca un registro en rango de fechas y paginado
     * 
     */
    @Query("SELECT d FROM DireccionCat d WHERE d.usuarioFecha BETWEEN ?1 AND ?2")
    List<DireccionCat> findByUsuarioFechaBetween(Date fechaInicio, Date fechaFin, Pageable pageable);

    /**
     * Insertar un registro.
     * 
     */
  //  @Query(value = "INSERT INTO direccion (elemento, direccion, navegador_dispositivo, usuario, usuario_fecha, usuario_programa) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
  //  Direccion insertDireccion(String elemento, String direccion, String navegadorDispositivo, String usuario, Date usuarioFecha, String usuarioPrograma);     
}
