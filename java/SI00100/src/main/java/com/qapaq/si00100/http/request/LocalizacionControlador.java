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
import com.qapaq.si00100.jpa.model.Localizacion;
import com.qapaq.si00100.servicio.LocalizacionServicio;

/**
 * Clase para controlar las peticiones de CRUD para servicio de localizaciones.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 */
@RestController
@RequestMapping(value = "/localizaciones")
public class LocalizacionControlador extends ComonControlador {

    LocalizacionServicio localizacionServicio;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    public LocalizacionControlador(LocalizacionServicio localizacionServicio) {
        this.localizacionServicio = localizacionServicio;
    }

    /**
     * Metodo para obtener todos las localizaciones.
     * 
     * @return
     */
    @GetMapping(value = "/")
    public List<Localizacion> getAllLocalizacion(Pageable pageable) {
        return localizacionServicio.findAllLocalizacion(pageable);
    }

    /**
     * Metodo para obtener una localizacion por id.
     * 
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Localizacion getLocalizacionById(@PathVariable Long id) {
        return localizacionServicio.findLocalizacionById(id);
    }

    /**
     * Metodo para obtener lista de localizacion por nombre y paginado.
     * 
     * @param localizacion
     * @return
     */
    @GetMapping(value = "/nombre={nombre}")
    public List<Localizacion> getLocalizacionByNombre(@PathVariable String nombre, Pageable pageable) {
        return localizacionServicio.findLocalizacionByNombre(nombre, pageable);
    }

    /**
     * Metodo para crear una localizacion.
     * 
     * @param localizacion
     * @return
     */
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Localizacion createLocalizacion(@Valid @RequestBody Localizacion localizacion, HttpServletRequest request) {
        return localizacionServicio.saveLocalizacion(localizacion, evaluarUsuario(request), appName + " " + appVersion);
    }

    /**
     * Metodo para actualizar una localizacion.
     * 
     * @param localizacion
     * @return
     */
    @PutMapping(value = "/", consumes = "application/json", produces = "application/json")
    @ResponseBody  
    public Localizacion updateLocalizacion(@Valid @RequestBody Localizacion localizacion, HttpServletRequest request) {
        return localizacionServicio.saveLocalizacion(localizacion, evaluarUsuario(request), appName + " " + appVersion);
    }

    /**
     * Metodo para borrar una localizacion.
     * 
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocalizacion(@PathVariable Long id) {
        localizacionServicio.deleteLocalizacionById(id);
    }
}
