package com.qapaq.ca00100.jpa.queries;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.qapaq.ca00100.jpa.model.NotificacionEventoCat;

/**
 * Interfaces para CRUD de JPA Notificacion.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-11
 * 
 */
public interface NotificacionEventoRepositorioCat extends PagingAndSortingRepository<NotificacionEventoCat, Long> {

    /**
     * Metodo para obtener una notificacion por id_notificacion y paginado. 
     */
    List<NotificacionEventoCat> findByIdNotificacion(Long idNotificacion, Pageable pageable);

    /**
     * Metodo para obtener una notificacion_evento por id_notificacion_evento.
     */
    @Query("SELECT a FROM NotificacionEventoCat a WHERE a.idNotificacionEvento = ?1")
    NotificacionEventoCat findByIdNotificacionEvento(Long idNotificacionEvento);

}