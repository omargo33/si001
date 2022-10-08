package com.qapaq.si.background.servicio;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si.background.jpa.model.Ciudad;
import com.qapaq.si.background.jpa.queries.CiudadRepositorio;


@Service
@Transactional
public class CiudadServicio {

    private final CiudadRepositorio ciudadRepositorio;

    /**
     * Constructor de la clase.
     */
    public CiudadServicio(CiudadRepositorio ciudadRepositorio) {
        this.ciudadRepositorio = ciudadRepositorio;
    }

    /**
     * Metodo para listar todas las ciudades.
     */
    public List<Ciudad> listarCiudades() {
        return ciudadRepositorio.findAll();
    }
}
