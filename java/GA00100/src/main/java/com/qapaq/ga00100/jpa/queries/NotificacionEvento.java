package com.qapaq.ga00100.jpa.queries;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.qapaq.ga00100.jpa.model.Notificacion;

/**
 * Interfaces para CRUD de JPA Notificacion.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-11
 * 
 */
public interface NotificacionEvento extends PagingAndSortingRepository<Notificacion, Long> {

    /**
     * Metodo para obtener una notificacion por id_notificacion y paginado. 
     */
    List<NotificacionEvento> findByIdNotificacion(Long idNotificacion, Pageable pageable);

    /**
     * Metodo para obtener una notificacion_evento por id_notificacion_evento.
     */
    @Query("SELECT a FROM NotificacionEvento a WHERE a.idNotificacionEvento = ?1")
    NotificacionEvento findByIdNotificacionEvento(Long idNotificacionEvento);

}