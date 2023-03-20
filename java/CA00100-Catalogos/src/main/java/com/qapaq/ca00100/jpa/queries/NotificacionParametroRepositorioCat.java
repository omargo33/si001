package com.qapaq.ca00100.jpa.queries;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qapaq.ca00100.jpa.model.NotificacionParametroCat;

/**
 * Interfaces para CRUD de JPA Notificacion Parametro.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-11
 * 
 */
public interface NotificacionParametroRepositorioCat extends PagingAndSortingRepository<NotificacionParametroCat, Long> {

    /**
     * Metodo para obtener un notificacion_parametro por id_notificacion_parametro.
     */
    NotificacionParametroCat findByIdNotificacionParametro(Long idNotificacionParametro);

    /**
     * Metodo para obtener las notificacion_parametro por id_notificacion.
     */
    @Query("SELECT a FROM NotificacionParametroCat a WHERE a.idNotificacion = ?1")
    List<NotificacionParametroCat> findAllByIdNotificacion(Long idNotificacion);
}
