package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si00100.jpa.model.Localizacion;
import com.qapaq.si00100.jpa.queries.LocalizacionRepositorio;

/**
 * Clase de servicio crud para Localizacion repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 */
@Service
@Transactional
public class LocalizacionServicio {

    private final LocalizacionRepositorio localizacionRepositorio;

    /**
     * Metodo para crear una localizacion.
     * 
     */
    public LocalizacionServicio(LocalizacionRepositorio localizacionRepositorio) {
        this.localizacionRepositorio = localizacionRepositorio;
    }

    /**
     * Metodo guardar informacion.
     * 
     * @param localizacion
     * @return
     */
    public Localizacion saveLocalizacion(Localizacion localizacion, String usuario, String usuarioPrograma) {
        localizacion.setUsuario(usuario);
        localizacion.setUsuarioFecha(new Date());
        localizacion.setUsuarioPrograma(usuarioPrograma);
        return localizacionRepositorio.save(localizacion);
    }

    /**
     * Metodo listar todos los localizacions de manera paginada
     */
    public List<Localizacion> findAllLocalizacion(Pageable pageable) {
        return localizacionRepositorio.findAllPage(pageable);
    }

    /**
     * Metodo para buscar un localizacion por id_localizacion.
     */
    public Localizacion findLocalizacionById(Long id) {
        return localizacionRepositorio.findByIdLocalizacion(id);
    }

    /**
     * Metodo para buscar un localizacion por nombre.
     *
     * 
     * @param nombre
     * @return
     */
    public List<Localizacion> findLocalizacionByNombre(String nombre, Pageable pageable) {
        return localizacionRepositorio.findAllByNombre(nombre, pageable);
    }

    /**
     * Metodo para verificar si existe una localizacion por id_localizacion.
     */
    public boolean isLocalizacionUnico(String nombre) {
        return localizacionRepositorio.existsByNombre(nombre);
    }

    /**
     * Metodo para eliminar un localizacion por id_localizacion.
     */
    public boolean isLocalizacionUnico(String nombre, Long idLocalizacion) {
        return localizacionRepositorio.existsByNombreAndIdLocalizacion(nombre, idLocalizacion);
    }

    /**
     * Metodo para eliminar una localizacion por id_localizacion.
     */
    public void deleteLocalizacionById(Long id) {
        localizacionRepositorio.deleteById(id);
    }
}