package com.qapaq.si00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qapaq.si00100.jpa.model.VRecursoMonitor;

/**
 * Interfaces CRUD para JPA VRecursoMonitor.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-24
 * 
 */
@Repository
public interface VRecursoMonitorRepositorio extends JpaRepository<VRecursoMonitor, Long> {

    /**
     * Buscar todos los v_recursos_monitores por network o wifi.
     */
    @Query("SELECT r FROM VRecursoMonitor r WHERE r.network = ?1 OR r.wifi = ?2")
    List<VRecursoMonitor> findAllByNetworkOrWifi(String network, String wifi);
}
