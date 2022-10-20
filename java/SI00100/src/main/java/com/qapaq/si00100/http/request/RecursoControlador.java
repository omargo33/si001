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

import com.qapaq.si00100.jpa.exception.ForeignKeyException;
import com.qapaq.si00100.jpa.model.Recurso;
import com.qapaq.si00100.servicio.RecursoServicio;

/**
 * Clase para controlar las peticiones de CRUD para servicio de Recursos.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-16
 * 
 */
@RestController
@RequestMapping(value = "/recursos")
public class RecursoControlador extends ComonControlador {

    private final RecursoServicio recursoServicio;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public RecursoControlador(RecursoServicio recursoServicio) {
        this.recursoServicio = recursoServicio;
    }

    /**
     * Metodo para obtener todos los recursos.
     */
    @GetMapping("/")
    public List<Recurso> getAllRecurso(Pageable pageable) {
        return recursoServicio.findAllRecurso(pageable);
    }

    /**
     * Metodo para obtener un recurso por id.
     */
    @GetMapping("/{id}")
    public Recurso getRecursoById(@PathVariable(value = "id") Long id) {
        return recursoServicio.findRecursoById(id);
    }

    /**
     * getMapping para dos variables
     */
    @GetMapping("/nombre={nombre}&idProyecto={idProyecto}")
    public List<Recurso> getRecursoById(@PathVariable(value = "nombre") String nombre,
            @PathVariable(value = "idProyecto") Long idProyecto, Pageable pageable) {
        return recursoServicio.findRecursoByNombreAndIdProyecto(nombre, idProyecto, pageable);
    }

    /**
     * Metodo para crear un recurso.
     * 
     * @param recurso
     * @return
     */
    @PostMapping("/")
    public Recurso createRecurso(@Valid @RequestBody Recurso recurso, HttpServletRequest request) {
        return recursoServicio.saveRecurso(recurso, evaluarUsuario(request), appName + " " + appVersion);
    }

    /**
     * Metodo para actualizar un recurso.
     */
    @PutMapping("/")
    public Recurso updateRecurso(@Valid @RequestBody Recurso recurso, HttpServletRequest request) {
        return recursoServicio.saveRecurso(recurso, evaluarUsuario(request), appName + " " + appVersion);
    }

    @DeleteMapping("/{id}")
    public void deleteRecurso(@PathVariable(value = "id") Long id) throws ForeignKeyException {
        recursoServicio.deleteRecursoById(id);
    }
}