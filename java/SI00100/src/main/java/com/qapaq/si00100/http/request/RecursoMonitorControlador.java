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
import com.qapaq.si00100.jpa.model.RecursoMonitor;
import com.qapaq.si00100.servicio.RecursoMonitorServicio;

/**
 * Clase para controlar las peticiones de CRUD para servicio de RecursosMonitores.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-16
 * 
 */
@RestController
@RequestMapping(value = "/recursos_monitores")
public class RecursoMonitorControlador  extends ComonControlador {
    private RecursoMonitorServicio recursoMonitorServicio;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public RecursoMonitorControlador(RecursoMonitorServicio recursoMonitorServicio) {
        this.recursoMonitorServicio = recursoMonitorServicio;
    }

    /**
     * Metodo para obtener un recursoMonitor por id_monitor y paginado
     */
    @GetMapping("/{id}")
    public RecursoMonitor getRecursoMonitorByIdRecursoMonitor(@PathVariable(value = "id") Long idRecursoMonitor) {
        return recursoMonitorServicio.findByIdRecursoMonitor(idRecursoMonitor);
    }
    
    /**
     * Metodo para obtener un recursoMonitor por id_monitor y paginado
     */
    @GetMapping("/idMonitor={idMonitor}")
    public List<RecursoMonitor> getRecursoMonitorByIdMonitor(@PathVariable(value = "idMonitor") Long idMonitor, Pageable pageable) {
        return recursoMonitorServicio.findAllByIdMonitor(idMonitor, pageable);
    }

    /**
     * Metodo para obtener un recursoMonitor por id_recurso y paginado
     */
    @GetMapping("/idRecurso={idRecurso}")
    public List<RecursoMonitor> getRecursoMonitorByIdRecurso(@PathVariable(value = "idRecurso") Long idRecurso, Pageable pageable) {
        return recursoMonitorServicio.findAllByIdRecurso(idRecurso, pageable);
    }

    /**
     * Metodo crear un recursoMonitor.
     */
    @PostMapping("/")
    public RecursoMonitor createRecursoMonitor(@Valid @RequestBody RecursoMonitor recursoMonitor) {
        return recursoMonitorServicio.saveRecursoMonitor(recursoMonitor,"getUSuario()", appName+" "+appVersion);
    }

    /**
     * Metodo para actualizar un recursoMonitor.
     */
    @PutMapping("/")
    public RecursoMonitor updateRecursoMonitor(@Valid @RequestBody RecursoMonitor recursoMonitor) {
        return recursoMonitorServicio.saveRecursoMonitor(recursoMonitor,"getUSuario()", appName+" "+appVersion);
    }

    /**
     * Metodo para borrar un recursoMonitor.
     */
    @DeleteMapping("/{id}")
    public void deleteRecursoMonitor(@PathVariable(value = "id") Long id) throws ForeignKeyException {        
        recursoMonitorServicio.deleteByIdRecursoMonitor(id);
    }






}
    

