package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
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
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor de la clase.
     * 
     * @param monitorRepositorio
     */
    public MonitorServicio(MonitorRepositorio monitorRepositorio, PasswordEncoder passwordEncoder) {
        this.monitorRepositorio = monitorRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Metodo guardar monitor.
     * 
     * y encriptar la clave.
     * 
     * @param monitor
     * @return
     */
    public Monitor saveMonitor(Monitor monitor, String usuario, String usuarioPrograma) {
        monitor.setEstado(StringUtils.truncate("A",8));
        monitor.setUsuario(StringUtils.truncate(usuario,128));
        monitor.setUsuarioFecha(new Date());
        monitor.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma,256));
        monitor.setClave(passwordEncoder.encode(monitor.getClave()));
        return monitorRepositorio.save(monitor);
    }

    /**
     * Metodo listar todos los climas de manera paginada
     */
    public List<Monitor> findAllMonitor(Pageable pageable) {
        return monitorRepositorio.findAllPage(pageable);
    }

    /**
     * Metodo para buscar un monitor por id_monitor.
     */
    public Monitor findMonitorById(Long id) {
        return monitorRepositorio.findByIdMonitor(id);
    }

    /**
     * Metodo para buscar un monitor por nombre.
     */

    /**
     * Metodo para buscar un monitor por nombre.
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
