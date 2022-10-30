package com.qapaq.si00100.http.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.http.request.ComonControlador;
import com.qapaq.si00100.jpa.model.RecursoMonitorPresentacion;
import com.qapaq.si00100.servicio.RecursoMonitorPresentacionServicio;

/**
 * Clase para controlar las peticiones de CRUD para servicio de
 * RecursosMonitoresCalendario.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-17
 * 
 */
@RestController
@RequestMapping(value = "/recursos_monitores_presentaciones")
public class RecursoMonitorPresentacionControlador extends ComonControlador {

    private RecursoMonitorPresentacionServicio recursoMonitorPresentacionServicio;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public RecursoMonitorPresentacionControlador(
            RecursoMonitorPresentacionServicio recursoMonitorPresentacionServicio) {
        this.recursoMonitorPresentacionServicio = recursoMonitorPresentacionServicio;
    }

    /**
     * Metodo para obtener un recursoMonitorPresentacion por
     * id_recursoMonitorPresentacion.
     */
    @GetMapping("/{id}")
    public RecursoMonitorPresentacion getRecursoMonitorPresentacionByIdRecursoMonitorPresentacion(
            @PathVariable(value = "id") Long idRecursoMonitorPresentacion) {
        return recursoMonitorPresentacionServicio.findByIdRecursoMonitorPresentacion(idRecursoMonitorPresentacion);
    }

    /**
     * Metodo para obtener un recursoMonitorPresentacion por id_monitor_recurso y
     * paginado
     */
    @GetMapping("/idRecursoMonitor={idRecursoMonitor}")
    public List<RecursoMonitorPresentacion> getRecursoMonitorPresentacionByIdMonitorRecurso(
            @PathVariable(value = "idRecursoMonitor") Long idMonitorRecurso, Pageable pageable) {
        return recursoMonitorPresentacionServicio.findByIdRecursoMonitorPresentacion(idMonitorRecurso, pageable);
    }

    /**
     * Metodo para crear un recursoMonitorPresentacion.
     */
    @PostMapping("/")
    public RecursoMonitorPresentacion createRecursoMonitorPresentacion(
            @Valid @RequestBody RecursoMonitorPresentacion recursoMonitorPresentacion, HttpServletRequest request) {
        return recursoMonitorPresentacionServicio.save(recursoMonitorPresentacion, evaluarUsuario(request),
                appName + " " + appVersion);
    }
}
