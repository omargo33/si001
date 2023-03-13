package com.qapaq.ga00100.jpa.queries;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qapaq.ga00100.jpa.model.NotificacionParametro;

/**
 * Interfaces para CRUD de JPA Notificacion Parametro.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-11
 * 
 */
public interface NotificacionParametroRepositorio extends PagingAndSortingRepository<NotificacionParametro, Long> {

    /**
     * Metodo para obtener un notificacion_parametro por id_notificacion_parametro.
     */
    NotificacionParametro findByIdNotificacionParametro(Long idNotificacionParametro);

    /**
     * Metodo para obtener las notificacion_parametro por id_notificacion.
     */
    @Query("SELECT a FROM NotificacionParametro a WHERE a.idNotificacion = ?1")
    List<NotificacionParametro> findAllByIdNotificacion(Long idNotificacion);
}
