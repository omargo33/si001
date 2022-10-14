package com.qapaq.si00100.http.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.si00100.jpa.exception.ForeignKeyException;
import com.qapaq.si00100.jpa.model.MonitorComando;
import com.qapaq.si00100.servicio.MonitorComandoServicio;

/**
 * Clase para controlar las peticiones de CRUD para servicio de monitor comando.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-13
 * 
 */
@RestController
@RequestMapping(value = "/monitores_comandos")
public class MonitorComandoControlador extends ComonControlador{

    MonitorComandoServicio monitorComandoServicio;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public MonitorComandoControlador(MonitorComandoServicio monitorComandoServicio) {
        this.monitorComandoServicio = monitorComandoServicio;
    }

    /**
     * Metodo para obtener todos los monitores comando.
     * 
     * @return
     */
    @GetMapping(value = "/")
    public List<MonitorComando> getAllMonitorComando(Pageable pageable) {
        return monitorComandoServicio.findAllMonitorComando(pageable);
    }

    /**
     * Metodo para obtener un monitor comando por id.
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public MonitorComando getMonitorComandoById(@PathVariable Long id) {
        return monitorComandoServicio.findMonitorComandoById(id);
    }

    /**
     * Metodo para obtener un monitor por nombre paginado.
     * 
     * @param monitorComando
     * @return
     */
    @GetMapping(value = "/elemento={elemento}")
    public List<MonitorComando> getMonitorComandoByNombre(@PathVariable String elemento, Pageable pageable) {
        return monitorComandoServicio.findAllMonitorComandoByElemento(elemento, pageable);
    }

    /**
     * Metodo para guardar un monitor comando.
     * 
     * @param monitorComando
     * @return
     */
    @PostMapping(value = "/")
    public MonitorComando saveMonitorComando(@Valid @RequestBody MonitorComando monitorComando) {
        return monitorComandoServicio.saveMonitorComando(monitorComando, "getUsuario()", appName + " " + appVersion);
    }

    /**
     * Metodo para actualizar un monitor comando.
     * 
     * @param monitorComando
     * @return
     */
    @PutMapping(value = "/")
    public MonitorComando updateMonitorComando(@Valid @RequestBody MonitorComando monitorComando) {
        return monitorComandoServicio.saveMonitorComando(monitorComando, "getUsuario()", appName + " " + appVersion);
    }

    /**
     * Metodo para eliminar un monitor comando.
     * 
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public void deleteMonitorComando(@PathVariable Long id) throws ForeignKeyException {
        monitorComandoServicio.deleteMonitorComandoById(id);
    }
}