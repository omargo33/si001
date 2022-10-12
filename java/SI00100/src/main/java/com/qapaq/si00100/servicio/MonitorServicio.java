package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si00100.jpa.model.Monitor;
import com.qapaq.si00100.jpa.queries.MonitorRepositorio;

/**
 * Clase de servicio CRUD para Monitor repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 */
@Service
@Transactional
public class MonitorServicio {

    private final MonitorRepositorio monitorRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param monitorRepositorio
     */
    public MonitorServicio(MonitorRepositorio monitorRepositorio) {
        this.monitorRepositorio = monitorRepositorio;
    }

    /**
     * Metodo guardar monitor.
     * 
     * @param monitor
     * @return
     */
    public Monitor saveMonitor(Monitor monitor, String usuario, String usuarioPrograma) {
        monitor.setEstado("A");
        monitor.setUsuario(usuario);
        monitor.setUsuarioFecha(new Date());
        monitor.setUsuarioPrograma(usuarioPrograma);
        return monitorRepositorio.save(monitor);
    }

    /**
     * Metodo listar todos los climas de manera paginada
     */
    public List<Monitor> findAllMonitor(Pageable pageable) {
        return monitorRepositorio.findAllPage(pageable);
    }

    /**
     * Metodo para buscar un clima por id_clima.
     */
    public Monitor findMonitorById(Long id) {
        return monitorRepositorio.findByIdMonitor(id);
    }

    /**
     * Metodo para buscar un clima por nombre.
     */
    public List<Monitor> findAllMonitorByNombre(String nombre, Pageable pageable) {
        return monitorRepositorio.findAllByNombre(nombre, pageable);
    }

    /**
     * Metodo para validar si existe un monitor con el nombre.
     */
    public boolean existsMonitorByNombre(String nombre) {
        return monitorRepositorio.existsByNombre(nombre);
    }

    /**
     * Metodo para validar si existe un monitor con el nombre y diferente idMonitor.
     */
    public boolean existsMonitorByNombreAndIdMonitor(String nombre, Long idMonitor) {
        return monitorRepositorio.existsByNombreAndIdMonitor(nombre, idMonitor);
    }

    /**
     * Metodo para validar si existe un monitor con la serie.
     */
    public boolean existsMonitorBySerie(String serie) {
        return monitorRepositorio.existsBySerie(serie);
    }

    /**
     * Metodo para validar si existe un monitor con la serie y diferente idMonitor.
     */
    public boolean existsMonitorBySerieAndIdMonitor(String serie, Long idMonitor) {
        return monitorRepositorio.existsBySerieAndIdMonitor(serie, idMonitor);
    }
    
    /**
     * Metodo para eliminar un monitor por idMonitor.
     */
    public void deleteMonitorById(Long id) {
        monitorRepositorio.deleteById(id);
    }
}
