package com.qapaq.si00100.http.request;

import java.util.List;

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

import com.qapaq.si00100.jpa.exception.ForeignKeyException;
import com.qapaq.si00100.jpa.model.Ciudad;
import com.qapaq.si00100.servicio.CiudadServicio;

/**
 * Clase para controlar las peticiones de las ciudades.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 */
@RestController
@RequestMapping(value = "/ciudades")
public class CiudadControlador extends ComonControlador{
    private CiudadServicio ciudadServicio;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    /**
     * Constructor de la clase.
     * 
     * @param ciudadServicio
     */
    @Autowired
    public CiudadControlador(CiudadServicio ciudadServicio) {
        this.ciudadServicio = ciudadServicio;
    }

    /**
     * Metodo para obtener todos las ciudades.
     * 
     * @return
     */
    @GetMapping(value = "/")
    public List<Ciudad> getAllCliente(Pageable pageable) {
        return ciudadServicio.findAllCiudad(pageable) ;        
    }

    /**
     * Metodo para obtener una ciudad por id_ciudad.
     */
    @GetMapping(value = "/{id}")
    public Ciudad getCiudadById(@PathVariable Long id) {
        return ciudadServicio.findCiudadByIdCiudad(id);
    }

    /**
     * Metodo para obtener una ciudad por nombre y paginado.
     */    
    @GetMapping("/nombre={nombre}")
    public List<Ciudad> getCiudadByNombre(@PathVariable String nombre, Pageable pageable) {
        return ciudadServicio.findCiudadByNombre(nombre, pageable);
    }

    /**
     * Metodo para crear una ciudad con un POST.
     * 
     * @param ciudad
     * @return
     */
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public @ResponseBody Ciudad createCiudad(@Valid @RequestBody Ciudad ciudad) {
        return ciudadServicio.saveCiudad(ciudad, "cambiar", appName+ " " + appVersion);
    }

    /**
     * Metodo para actualizar una ciudad con un PUT.     
      * 
      * @param ciudad
      * @return
      */
    @PutMapping(value = "/", consumes = "application/json", produces = "application/json")    
    public @ResponseBody Ciudad updateCiudad(@Valid @RequestBody Ciudad ciudad) {
        return ciudadServicio.saveCiudad(ciudad, "cambiar", appName+ " " + appVersion);
    }

    /**
     * Metodo para eliminar una ciudad con un DELETE.
     * 
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCiudad(@PathVariable Long id) throws ForeignKeyException{
        ciudadServicio.deleteCiudadById(id);
    }
}
