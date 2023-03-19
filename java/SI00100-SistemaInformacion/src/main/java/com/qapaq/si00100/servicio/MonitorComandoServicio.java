package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si00100.jpa.model.MonitorComando;
import com.qapaq.si00100.jpa.queries.MonitorComandoRepositorio;

/**
 * Clase de servicio CRUD para Monitor Comando Repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 */
@Service
@Transactional
public class MonitorComandoServicio {

    private final MonitorComandoRepositorio monitorComandoRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param monitorComandoRepositorio
     */
    public MonitorComandoServicio(MonitorComandoRepositorio monitorComandoRepositorio) {
        this.monitorComandoRepositorio = monitorComandoRepositorio;
    }

    /**
     * Metodo guardar monitor comando.
     * 
     * @param monitorComando
     * @return
     */
    public MonitorComando saveMonitorComando(MonitorComando monitorComando, String usuario, String usuarioPrograma) {
        monitorComando.setUsuario(usuario);
        monitorComando.setUsuarioFecha(new Date());
        monitorComando.setUsuarioPrograma(usuarioPrograma);
        return monitorComandoRepositorio.save(monitorComando);
    }

    /**
     * Metodo listar todos los monitores comando de manera paginada
     */
    public List<MonitorComando> findAllMonitorComando(Pageable pageable) {
        return monitorComandoRepositorio.findAllPage(pageable);
    }

    /**
     * Metodo para buscar un monitor comando por id_monitor_comando.
     */
    public MonitorComando findMonitorComandoById(Long id) {
        return monitorComandoRepositorio.findByIdMonitorComando(id);
    }

    /**
     * Metodo para buscar un monitor comando por like elemento y paginado.
     */
    public List<MonitorComando> findAllMonitorComandoByElemento(String elemento, Pageable pageable) {
        return monitorComandoRepositorio.findByElemento(elemento, pageable);
    }

    /**
     * Metodo para borrar monitor comando.
     */
    public void deleteMonitorComandoById(Long id) {
        monitorComandoRepositorio.deleteById(id);
    }
}
