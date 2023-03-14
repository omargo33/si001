package com.qapaq.seguridades.jpa.queries;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qapaq.seguridades.jpa.model.Direccion;

/**
 * Interface para la clase Direccion.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-11-25
 * 
 */
@Repository
public interface DireccionRepositorio extends JpaRepository<Direccion, Long> {

    /**
     * Busca un registro por su id.
     */
    Direccion findByIdDireccion(Long idDireccion);

    /**
     * Busca un registro en rango de fechas y paginado
     * 
     */
    @Query("SELECT d FROM Direccion d WHERE d.usuarioFecha BETWEEN ?1 AND ?2")
    List<Direccion> findByUsuarioFechaBetween(Date fechaInicio, Date fechaFin, Pageable pageable);

    /**
     * Insertar un registro.
     * 
     */
    @Query(value = "INSERT INTO direccion (elemento, direccion, navegador_dispositivo, usuario, usuario_fecha, usuario_programa) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    Direccion insertDireccion(String elemento, String direccion, String navegadorDispositivo, String usuario, Date usuarioFecha, String usuarioPrograma);     
}
