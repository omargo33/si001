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
import com.qapaq.si00100.jpa.model.Monitor;
import com.qapaq.si00100.servicio.MonitorServicio;

/**
 * Clase para controlar las peticiones de CRUD para servicio de monitores.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 */
@RestController
@RequestMapping(value = "/monitores")
public class MonitorControlador extends ComonControlador {
    
        MonitorServicio monitorServicio;
    
        @Value("${app.name}")
        private String appName;
    
        @Value("${app.version}")
        private String appVersion;
    
        @Autowired
        public MonitorControlador(MonitorServicio monitorServicio) {
            this.monitorServicio = monitorServicio;
        }
    
        /**
        * Metodo para obtener todos los monitores.
        * 
        * @return
        */
        @GetMapping(value = "/")
        public List<Monitor> getAllMonitor(Pageable pageable) {
            return monitorServicio.findAllMonitor(pageable);
        }
    
        /**
        * Metodo para obtener un monitor por id.
        * 
        * @param id
        * @return
        */
        @GetMapping(value = "/{id}")
        public Monitor getMonitorById(@PathVariable Long id) {
            return monitorServicio.findMonitorById(id);
        }
    
        /**
        * Metodo para obtener lista de monitores por nombre y paginado.
        * 
        * @param monitor
        * @return
        */
        @GetMapping(value = "/nombre={nombre}")
        public List<Monitor> getMonitorByNombre(@PathVariable String nombre, Pageable pageable) {
            return monitorServicio.findAllMonitorByNombre(nombre, pageable);
        }
    
        /**
        * Metodo para crear un monitor.
        * 
        * @param monitor
        * @return
        */
        @PostMapping(value = "/")
        public Monitor createMonitor(@Valid @RequestBody Monitor monitor) {
            return monitorServicio.saveMonitor(monitor, "usuario()", appName+" "+appVersion);
        }
    
        /**
        * Metodo para actualizar un monitor.
        * 
        * @param monitor
        * @return
        */
        @PutMapping(value = "/")
        public Monitor updateMonitor(@Valid @RequestBody Monitor monitor) {
            return monitorServicio.saveMonitor(monitor,"usuario()", appName+" "+appVersion);
        }
    
        /**
        * Metodo para eliminar un monitor.
        * 
        * @param id
        */
        @DeleteMapping(value = "/{id}")
        public void deleteMonitorComando(@PathVariable Long id) throws ForeignKeyException {        
            monitorServicio.deleteMonitorById(id);
        }    
    }