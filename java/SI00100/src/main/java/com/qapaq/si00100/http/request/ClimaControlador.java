package com.qapaq.si00100.http.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.si00100.jpa.exception.ForeignKeyException;
import com.qapaq.si00100.jpa.model.Clima;
import com.qapaq.si00100.jpa.queries.climaServicio;
import com.qapaq.si00100.servicio.ClimaServicio;

/**
 * Clase para controlar las peticiones del clima.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-11
 * 
 */
@RestController
@RequestMapping(value = "/climas")
public class ClimaControlador extends ComonControlador {

    private ClimaServicio climaServicio;

    /**
     * Constructor de la clase.
     * 
     * @param climaServicio
     */
    @Autowired
    public ClimaControlador(ClimaServicio climaServicio) {
        this.climaServicio = climaServicio;
    }

    /**
     * Metodo para obtener todos las clima paginados.
     * 
     * @return
     */
    @GetMapping("/")
    public List<Clima> getAllClima(Pageable pageable) {
        return climaServicio.findAllClimas(pageable);
    }

    /**
     * Metodo para obtener todos el clima para id_clima.
     */
    @GetMapping("/{id_clima}")
    public Clima getClima(@PathVariable("id_clima") Long idClima) {
        return climaServicio.findClimaById(idClima);
    }

    /**
     * Metodo para obtener todos el clima para id_ciudad.
     */
    @GetMapping("/idCiudad={idCiudad}")
    public List<Clima> getClimaCiudad(@PathVariable(name = "idCiudad") Long idCiudad, Pageable pageable) {
        return climaServicio.findClimaByIdCiudad(idCiudad, pageable);
    }

    /**
     * Metodo para delete el clima para id_clima.
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClima(@PathVariable Long id)  throws ForeignKeyException{
        climaServicio.deleteClimaByIdClima(id);
    }    
}
