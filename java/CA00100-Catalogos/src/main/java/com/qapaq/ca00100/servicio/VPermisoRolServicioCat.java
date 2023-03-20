package com.qapaq.ca00100.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ca00100.jpa.model.VPermisoRolCat;
import com.qapaq.ca00100.jpa.queries.VPermisoRolRepositorioCat;

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
public class VPermisoRolServicioCat {

    @Autowired
    private final VPermisoRolRepositorioCat vPermisoRepositorio;

    /**
     * Metodo para mostrar vpermisos por nick y indice_modulo.
     */
    public List<VPermisoRolCat> findByNickAndIndiceModulo(String indiceModulo) {
        return vPermisoRepositorio.findByIndiceModulo(indiceModulo);
    }    
}
