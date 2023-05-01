package com.qapaq.si.background.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.si.background.jpa.model.Monitor;

/**
 * Class para dar repositorio para spring del jpa Monitor.java
 * 
 * @author: o.velez@qapaq.io
 * @date: 2020/08/14
 * 
 */
public interface MonitorRepositorio extends JpaRepository<Monitor, Long> {

    /**
     * Metodo para buscar un objeto por id.
     */
    public Monitor findByIdMonitor(Long id);

    /**
     * Metodo para buscar por wifi o ip un solo monitor.
     */
    public Monitor findByWifiOrIp(String wifi, String ip);
    
    /**
     * Metodo para buscar todos los objetos.
     */
    public List<Monitor> findAll();    
}
