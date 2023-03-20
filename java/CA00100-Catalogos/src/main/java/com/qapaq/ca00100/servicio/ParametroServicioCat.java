package com.qapaq.ca00100.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ca00100.jpa.model.ModuloCat;
import com.qapaq.ca00100.jpa.model.ParametroCat;
import com.qapaq.ca00100.jpa.queries.ParametroRepositorioCat;

import lombok.RequiredArgsConstructor;

/**
 * Objeto para dar soporte a servicio REST de Parametro.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-12-08
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ParametroServicioCat {

    @Autowired
    private ParametroRepositorioCat parametroRepositorio;
    @Autowired
    private ModuloServicioCat moduloServicio;


    /**
     * Metodo para buscar parametros de un modulo.
     * 
     */
    public List<ParametroCat> findByIdModulo(Long idModulo) {
        return parametroRepositorio.findByIdModulo(idModulo);        
    }

    /**
     * Metodo para buscar parametros de un modulo.
     * 
     * y convertir en mapa de parametros.
     * 
     * @param indiceModulo
     * @return
     */
    public Map<String,ParametroCat> findByIndiceModulo(String indiceModulo) {
        Map<String,ParametroCat> mapParametros = new HashMap<>();         
        ModuloCat modulo = moduloServicio.findByIndice(indiceModulo);
        List <ParametroCat> parametros = parametroRepositorio.findByIdModulo(modulo.getIdModulo());
        for (ParametroCat parametro : parametros) {            
            mapParametros.put(parametro.getIndice(), parametro);            
        }

        return mapParametros;
    }

    /**
     * Metodo para buscar un parametro de un modulo.
     * 
      * @param indiceModulo
      * @param indiceParametro
      * @return
      */
    public ParametroCat findByIdModuloAndIndice(String indiceModulo, String indiceParametro) {
        ModuloCat modulo = moduloServicio.findByIndice(indiceModulo);
        return parametroRepositorio.findByIdModuloAndIndice(modulo.getIdModulo(), indiceParametro);
    }
}
