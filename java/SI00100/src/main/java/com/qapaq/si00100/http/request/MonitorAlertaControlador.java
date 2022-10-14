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
import com.qapaq.si00100.jpa.model.MonitorAlerta;
import com.qapaq.si00100.servicio.MonitorAlertaServicio;

/**
 * Clase para controlar las peticiones de CRUD para servicio de monitores
 * alertas.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-13
 * 
 */
@RestController
@RequestMapping(value = "/monitores_alertas")
public class MonitorAlertaControlador extends ComonControlador {

    MonitorAlertaServicio monitorAlertaServicio;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public MonitorAlertaControlador(MonitorAlertaServicio monitorAlertaServicio) {
        this.monitorAlertaServicio = monitorAlertaServicio;
    }

    /**
     * Metodo para obtener todos los monitores alertas.
     * 
     * @return
     */
    @GetMapping(value = "/")
    public List<MonitorAlerta> getAllMonitorAlerta(Pageable pageable) {
        return monitorAlertaServicio.findAllMonitorAlerta(pageable);
    }

    /**
     * Metodo para obtener un monitor alerta por id.
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public MonitorAlerta getMonitorAlertaById(@PathVariable("id") Long id) {
        return monitorAlertaServicio.findMonitorAlertaById(id);
    }

     /**
     * Metodo para obtener clientes por razon social.
     * 
     * @param razonSocial
     * @return
     */
    @GetMapping("/nombre={nombre}")
    public List<MonitorAlerta> getClienteByNombre(@PathVariable(name = "nombre") String nombre,
            Pageable pageable) {
        return monitorAlertaServicio.findMonitorAlertaByNombre(nombre, pageable);
    }

    /**
     * Metodo para guardar un monitor alerta.
     * 
     * @param monitorAlerta
     * @return
     */
    @PostMapping(value = "/")
    public MonitorAlerta saveMonitorAlerta(@Valid @RequestBody MonitorAlerta monitorAlerta) {
        return monitorAlertaServicio.saveMonitorAlerta(monitorAlerta, " getUsuario()", appName + " " + appVersion);
    }

    /**
     * Metodo para actualizar un monitor alerta.
     * 
     * @param monitorAlerta
     * @return
     */
    @PutMapping(value = "/")
    public MonitorAlerta updateMonitorAlerta(@Valid @RequestBody MonitorAlerta monitorAlerta) {
        return monitorAlertaServicio.saveMonitorAlerta(monitorAlerta, " getUsuario()", appName + " " + appVersion);
    }

    /**
     * Metodo para eliminar un monitor alerta.
     * 
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    public void deleteMonitorAlerta(@PathVariable("id") Long id) throws ForeignKeyException {
        monitorAlertaServicio.deleteMonitorAlertaById(id);
    }
}