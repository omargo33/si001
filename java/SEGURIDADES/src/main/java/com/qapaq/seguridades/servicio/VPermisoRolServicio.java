package com.qapaq.seguridades.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.seguridades.jpa.queries.VPermisoRolRepositorio;
import com.qapaq.seguridades.jpa.model.VPermisoRol;

/**
 * Objeto para dar soporte a servicio REST de vpermiso
 * 
 * @author o.velez@qapaq.io
 * @date 2020-12-12
 * 
 */
@Service
@Transactional
public class VPermisoRolServicio {

    private final VPermisoRolRepositorio vPermisoRepositorio;

    /**
     * Metodo para crear los repositorios.
     * @param vPermisoRepositorio
     *
     */
    @Autowired
    public VPermisoRolServicio(VPermisoRolRepositorio vPermisoRepositorio) {
        this.vPermisoRepositorio = vPermisoRepositorio;
    }

    /**
     * Metodo para mostrar vpermisos por nick y indice_modulo.
     */
    public List<VPermisoRol> findByNickAndIndiceModulo(String indiceModulo) {
        return vPermisoRepositorio.findByIndiceModulo(indiceModulo);
    }    
}
