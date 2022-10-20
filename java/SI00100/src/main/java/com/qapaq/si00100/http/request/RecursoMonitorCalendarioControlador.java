package com.qapaq.si00100.http.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.qapaq.si00100.servicio.RecursoMonitorCalendarioServicio;
import com.qapaq.si00100.jpa.exception.ForeignKeyException;
import com.qapaq.si00100.jpa.model.RecursoMonitorCalendario;

/**
 * Clase para controlar las peticiones de CRUD para servicio de RecursosMonitoresCalendario.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-17
 * 
 */
@RestController
@RequestMapping(value = "/recursos_monitores_calendarios")
public class RecursoMonitorCalendarioControlador extends ComonControlador {

    private RecursoMonitorCalendarioServicio recursoMonitorCalendarioServicio;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;
    
    @Autowired
    public RecursoMonitorCalendarioControlador(RecursoMonitorCalendarioServicio recursoMonitorCalendarioServicio) {
        this.recursoMonitorCalendarioServicio = recursoMonitorCalendarioServicio;
    }

    /**
     * Metodo para obtener un recursoMonitorCalendario por id_recursoMonitorCalendario.
     */
    @GetMapping("/{id}")
    public RecursoMonitorCalendario getRecursoMonitorCalendarioByIdRecursoMonitorCalendario(@PathVariable(value = "id") Long idRecursoMonitorCalendario) {
        return recursoMonitorCalendarioServicio.findByIdRecursoMonitorCalendario(idRecursoMonitorCalendario);
    }

    /**
     * Metodo para obtener un recursoMonitorCalendario por id_monitor_recurso y paginado
     */
    @GetMapping("/idRecursoMonitor={idRecursoMonitor}")
    public List<RecursoMonitorCalendario> getRecursoMonitorCalendarioByIdMonitorRecurso(@PathVariable(value = "idRecursoMonitor") Long idMonitorRecurso, Pageable pageable) {
        return recursoMonitorCalendarioServicio.findByIdRecursoMonitorCalendario(idMonitorRecurso, pageable);
    }

    /**
     * Metodo para crear un recursoMonitorCalendario
     */
    @PutMapping("/")
    public RecursoMonitorCalendario createRecursoMonitorCalendario(@Valid @RequestBody RecursoMonitorCalendario recursoMonitorCalendario, HttpServletRequest request) {
        return recursoMonitorCalendarioServicio.save(recursoMonitorCalendario,evaluarUsuario(request), appName+" "+appVersion);
    } 

    /**
     * Metodo para actualizar un recursoMonitorCalendario
     */
    @PostMapping("/")
    public RecursoMonitorCalendario updateRecursoMonitorCalendario(@Valid @RequestBody RecursoMonitorCalendario recursoMonitorCalendario, HttpServletRequest request) {
        return recursoMonitorCalendarioServicio.save(recursoMonitorCalendario, evaluarUsuario(request), appName+" "+appVersion);
    }

    /**
     * Metodo para eliminar un recursoMonitorCalendario
     */
    @DeleteMapping("/{id}")
    public void deleteRecursoMonitorCalendario(@PathVariable(value = "id") Long idRecursoMonitorCalendario) throws ForeignKeyException {
        recursoMonitorCalendarioServicio.deleteByIdRecursoMonitorCalendario(idRecursoMonitorCalendario);
    }    
}
