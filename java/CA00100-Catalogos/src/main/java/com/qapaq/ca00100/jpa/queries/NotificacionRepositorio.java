package com.qapaq.ca00100.jpa.queries;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.ca00100.jpa.model.Notificacion;


/**
 * Interfaces para CRUD de JPA Notificacion.
 */
@Repository
public interface NotificacionRepositorio extends PagingAndSortingRepository<Notificacion, Long> {

    /**
     * Metodo para obtener una notificacion por id_notificacion.
     */
    Notificacion findByIdNotificacion(Long idNotificacion);

    /**
     * Metodo para obtener las notificaciones buscadas por like indice y like nombre y este paginado de formato nativo.
     */
    @Query(value = "SELECT * FROM notificacion n WHERE n.indice LIKE %?1% AND n.nombre LIKE %?2%", nativeQuery = true)
    List<Notificacion> findByIndiceAndNombre(String indice, String nombre, Pageable pageable);

    /**
     * Metodo para obtener todas las notificaciones con el estado pendiente y paginado.
     */
    @Query("SELECT n FROM Notificacion n WHERE n.estado = 'P'")
    List<Notificacion> findAllPendientes(Pageable pageable);

    /**
     * Metodo actualizar el estado.
     */
    @Query("UPDATE Notificacion n SET n.estado = ?1 WHERE n.idNotificacion = ?2")
    void updateEstado(String estado, Long idNotificacion);
}
