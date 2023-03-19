package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si00100.jpa.model.Informacion;
import com.qapaq.si00100.jpa.queries.InformacionRepositorio;

/**
 * Clase de servicio crud para Informacion repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 */
@Service
@Transactional
public class InformacionServicio {

    private final InformacionRepositorio informacionRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param informacionRepositorio
     */
    public InformacionServicio(InformacionRepositorio informacionRepositorio) {
        this.informacionRepositorio = informacionRepositorio;
    }

    /**
     * Metodo guardar informacion.
     * 
     * @param informacion
     * @return
     */
    public Informacion saveInformacion(Informacion informacion, String usuario, String usuarioPrograma) {
        informacion.setUsuario(usuario);
        informacion.setUsuarioFecha(new Date());
        informacion.setUsuarioPrograma(usuarioPrograma);
        return informacionRepositorio.save(informacion);        
    }

    /**
     * Metodo listar todos los climas de manera paginada
     */
    public List<Informacion> findAllInformacion(Pageable pageable) {
        return informacionRepositorio.findAllPage(pageable);
    }

    /**
     * Metodo para buscar un clima por id_clima.
     */
    public Informacion findInformacionById(Long id) {
        return informacionRepositorio.findByIdInformacion(id);
    }

    /**
     * Metodo para buscar por nombre y paginado.
     */
    public List<Informacion> findInformacionByNombre(String nombre, Pageable pageable) {
        return informacionRepositorio.findAllByNombre(nombre, pageable);
    }

    /**
     * Metodo para eliminar un clima por id_clima.
     */
    public void deleteInformacionById(Long id) {
        informacionRepositorio.deleteById(id);
    }

    /**
     * Metodo para validar si existe un clima con el nombre.
     *
     * @param nombre
     * @return
     */
    public boolean isInformacionUnico(String nombre){
        return informacionRepositorio.existsByNombre(nombre);
    }

    /**
     * Metodo para validar si existe un clima con el nombre y diferente id_informacion.
     *
     * @param nombre
     * @param id
     * @return
     */
    public boolean isInformacionUnico(String nombre, Long id){
        return informacionRepositorio.existsByNombreAndIdInformacion(nombre, id);
    }
}
