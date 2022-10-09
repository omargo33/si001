package com.qapaq.si00100.servicio;

import java.util.Date;
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
 
    /**
     * Constructor de la clase.
     * 
     * @param ciudadRepositorio
     */
    public CiudadServicio(CiudadRepositorio ciudadRepositorio) {
        this.ciudadRepositorio = ciudadRepositorio;
    }
 
    /**
     * Metodo para hacer un guardado personalizado.
     * 
     * @param ciudad
     * @param usuario     
     * @param usuarioPrograma
     * 
     * @return
     */
    public Ciudad saveCiudad(Ciudad ciudad, String usuario, String usuarioPrograma) {        
        ciudad.setUsuario(usuario);
        ciudad.setUsuarioFecha(new Date());
        ciudad.setUsuarioPrograma(usuarioPrograma);
        return ciudadRepositorio.save(ciudad);
    }

    /**
     * Metodo para borrar la ciudad por id_ciudad.
     * 
     * @param pageable
     * @return
     */
    public void deleteCiudadById(Long id) {        
        ciudadRepositorio.deleteById(id);        
    }

    /**
     * Metodo para obtener todas las ciudades.
     *   
     * @param pageable
     * @return
     */
    public List<Ciudad> findAllCiudad(Pageable pageable) {        
        return ciudadRepositorio.findAllPage(pageable);
    }
 
    /**
     * Metodo para obtener una ciudad por id.
     * 
     * @param id
     * @return
     */
    public Ciudad findCiudadByIdCiudad(Long id) {
        return ciudadRepositorio.findByIdCiudad(id);
    }
 
    /**
     * Metodo para obtener una ciudad por nombre.
     * 
     * @param nombre
     * @param pageable
     * @return
     */        
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
