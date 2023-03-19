package com.qapaq.ca00100.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ca00100.jpa.model.VPermisoRol;
import com.qapaq.ca00100.jpa.queries.VPermisoRolRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Objeto para dar soporte a servicio REST de vpermiso
 * 
 * @author o.velez@qapaq.io
 * @date 2020-12-12
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class VPermisoRolServicio {

    @Autowired
    private final VPermisoRolRepositorio vPermisoRepositorio;

    /**
     * Metodo para mostrar vpermisos por nick y indice_modulo.
     */
    public List<VPermisoRol> findByNickAndIndiceModulo(String indiceModulo) {
        return vPermisoRepositorio.findByIndiceModulo(indiceModulo);
    }    
}
