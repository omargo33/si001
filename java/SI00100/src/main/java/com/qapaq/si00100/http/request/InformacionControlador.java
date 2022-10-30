package com.qapaq.si00100.http.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.http.request.ComonControlador;
import com.qapaq.jpa.exception.ForeignKeyException;
import com.qapaq.si00100.jpa.model.Informacion;
import com.qapaq.si00100.servicio.InformacionServicio;

/**
 * Clase para controlar las peticiones de CRUD para servicio de Informacion.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 */
@RestController
@RequestMapping(value = "/informaciones")
public class InformacionControlador extends ComonControlador{

    InformacionServicio informacionServicio;
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public InformacionControlador(InformacionServicio informacionServicio) {
        this.informacionServicio = informacionServicio;
    }

    /**
     * Metodo para obtener todos las informaciones.
     * 
     * @return
     */
    @GetMapping(value = "/")
    public List<Informacion> getAllInformacion(Pageable pageable) {
        return informacionServicio.findAllInformacion(pageable);
    }

    /**
     * Metodo para obtener una informacion por id.
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Informacion getInformacionById(@PathVariable Long id) {
        return informacionServicio.findInformacionById(id);
    }

    /**
     * Metodo para obtener listado de informaciones por nombre y paginado.
     * 
     * @param nombre
     * @return
     */
    @GetMapping(value = "/nombre={nombre}")
    public List<Informacion> getInformacionByNombre(@PathVariable String nombre, Pageable pageable) {
        return informacionServicio.findInformacionByNombre(nombre, pageable);
    }

    /**
     * Metodo para guardar una informacion.
     * 
     * @param informacion
     * @return
     */
    @PostMapping(value = "/")
    public @ResponseBody Informacion saveInformacion(@Valid @RequestBody Informacion informacion, HttpServletRequest request) {
        return informacionServicio.saveInformacion(informacion, evaluarUsuario(request), appName + " " + appVersion);
    }

    /**
     * Metodo para actualizar una informacion.
     * 
     * @param informacion
     * @return
     */
    @PutMapping(value = "/")
    public void updateInformacion(@Valid @RequestBody Informacion informacion, HttpServletRequest request) {
        informacionServicio.saveInformacion(informacion, evaluarUsuario(request), appName + " " + appVersion);
    }

    /**
     * Metodo para eliminar una informacion.
     * 
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInformacion(@PathVariable Long id) throws ForeignKeyException {
            informacionServicio.deleteInformacionById(id);
    }    
}
