package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.si00100.jpa.model.Clima;
import com.qapaq.si00100.jpa.queries.ClimaRepositorio;

/**
 * Clase de servicio para Informacion.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-09
 * 
 */
@Service
@Transactional
public class ClimaServicio {

    private final ClimaRepositorio climaRepositorio;

    public ClimaServicio(ClimaRepositorio climaRepositorio) {
        this.climaRepositorio = climaRepositorio;
    }

    /**
     * Metodo guardar informacion.
     * 
     * @param ciudad
     * @return
     */
    public void saveClima(Clima clima, String usuario, String usuarioPrograma) {        
        clima.setUsuario(usuario);
        clima.setUsuarioFecha(new Date());
        clima.setUsuarioPrograma(usuarioPrograma);
        climaRepositorio.save(clima);
    }

    /**
     * Metodo para borrar la ciudad por id_ciudad.
     * 
     * @return
     */
    public void deleteClimaById(Long id) {        
        climaRepositorio.deleteById(id);
    }

    /**
     * Metodo listar todos los climas de manera paginada
     */
    public List<Clima> findAllClimas(Pageable pageable) {
        return climaRepositorio.findAllPage(pageable);
    }

    /**
     * Metodo para buscar un clima por id_clima.
     */
    public Clima findClimaById(Long id) {
        return climaRepositorio.findByIdClima(id);
    }

    /**
     * Metodo para listar climas por nombre, y paginado.
     */
    public List<Clima> findClimaByNombre(String nombre, Pageable pageable) {
        return climaRepositorio.findByNombre(nombre, pageable);
    }

}
