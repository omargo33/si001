package com.qapaq.ca00100.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ca00100.jpa.model.Modulo;
import com.qapaq.ca00100.jpa.queries.ModuloRepositorio;

import lombok.RequiredArgsConstructor;


/**
 * Objeto para dar soporte a servicio REST de modulo
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-12
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ModuloServicio {

    @Autowired
    private final ModuloRepositorio moduloRepositorio;
    
    /**
     * Metodo para mostrar todos los modulos.
     */
    public List<Modulo> findAllModulos(Pageable pageable) {
        return moduloRepositorio.findAllByEstadoNot(pageable);
    }

    /**
     * Metodo para mostrar un modulo por id.
     * 
     * @param id
     */
    public Modulo findByIdModulo(Long id) {               
        return moduloRepositorio.findByIdModulo(id);
    }

    /**
     * Metodo para buscar lista de modulos por indice.
     * 
     * @param indice
     * @param pageable
     * @return
     */
    public List<Modulo> findByIndiceModulo(String indice, Pageable pageable){
        return moduloRepositorio.findByIndiceAndEstadoNot(indice, pageable);
    }

    /**
     * Metodo para buscar un modulo por indice.
     * 
     * @param indice
     * 
     */
    public Modulo findByIndice(String indice) {
        return moduloRepositorio.findByIndice(indice);
    }

    /**
     * Metodo para buscar lista de modulos por nombre.
     * 
     */
    public List<Modulo> findByNombreModulo(String nombre, Pageable pageable){
        return moduloRepositorio.findByNombreLike( nombre , pageable);
    }

    /**
     * Metodo para crear un modulo.
     * 
     * @param modulo
     * @param usuario
     * @param usuarioPrograma
     * @return
     */
    /*public Modulo guardarModulo(Modulo modulo, String usuario, String usuarioPrograma) {
        modulo.setEstado(ConstantesGS00100.MODULO_ESTADO_ACTIVO);        
        modulo.setUsuario(StringUtils.truncate(usuario, 128));        
        modulo.setUsuarioFecha(new Date());
        modulo.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));
        return moduloRepositorio.save(modulo);
    } */

    /**
     * Metodo para borrar un modulo.
     * 
     * @param id
     */
    public void borrarModulo(Long id) {
        moduloRepositorio.deleteByIdModulo(id);
    }

    /**
     * Metodo para validar indice.
     * 
     */
    public boolean isIndiceUnico(String indice) {
        return moduloRepositorio.existsByIndice(indice);
    }

    /**
     * Metodo para validar indice diferente idModulo.
     * 
     */
    public boolean isIndiceUnico(String indice, Long idModulo) {
        return moduloRepositorio.existsByIndiceAndIdModulo(indice, idModulo);
    }

    /**
     * Metodo para validar nombre.
     * 
     */
    public boolean isNombreUnico(String nombre) {
        return moduloRepositorio.existsByNombre(nombre);
    }

    /**
     * Metodo para validar nombre diferente idModulo.
     * 
     */
    public boolean isNombreUnico(String nombre, Long idModulo) {
        return moduloRepositorio.existsByNombreAndIdModulo(nombre, idModulo);
    }
}