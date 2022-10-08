package com.qapaq.si00100.servicio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qapaq.si00100.jpa.model.Ciudad;
import com.qapaq.si00100.jpa.queries.CiudadRepositorio;

/**
 * Servicio de clientes.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-02
 * 
 */
@Service
@Transactional
public class CiudadServicio {
 
    private final CiudadRepositorio ciudadRepositorio;
 
    public CiudadServicio(CiudadRepositorio ciudadRepositorio) {
        this.ciudadRepositorio = ciudadRepositorio;
    }
 
    public List<Ciudad> findAllCiudad(Pageable pageable) {        
        return ciudadRepositorio.findAllPage(pageable);
    }
 
    public Ciudad findCiudadByIdCiudad(Long id) {
        return ciudadRepositorio.findAllByIdCiudad(id);
    }
 
    public List<Ciudad> findCiudadByNombre(String nombre, Pageable pageable) {
        return ciudadRepositorio.findAllByNombre(nombre, pageable);
    }

    /**
     * Metodo para validar si existe un ciudad unico por nombre.
     */
    public boolean isCiudadUnico(String ciudad) {
        return ciudadRepositorio.existsByNombre(ciudad);
    }    

    /**
     * Metodo para validar si existe un ciudad unico por nombre y diferente idCiudad.
     */
    public boolean isCiudadUnico(String ciudad, Long idCiudad) {
        return ciudadRepositorio.existsByNombreAndIdCiudad(ciudad, idCiudad);
    }
}
