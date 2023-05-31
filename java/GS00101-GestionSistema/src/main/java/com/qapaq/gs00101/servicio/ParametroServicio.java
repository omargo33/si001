package com.qapaq.gs00101.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00101.jpa.model.Modulo;
import com.qapaq.gs00101.jpa.model.Parametro;
import com.qapaq.gs00101.jpa.queries.ParametroRepositorio;

import lombok.RequiredArgsConstructor;

/**
 * Objeto para dar soporte a servicio REST de Parametro.
 * 
 * @author o.velez@qapaq.io
 * @since 2020-12-08
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ParametroServicio {

    @Autowired
    private ParametroRepositorio parametroRepositorio;

    @Autowired
    private ModuloServicio moduloServicio;


    /**
     * Metodo para buscar parametros de un modulo.
     * 
     */
    public List<Parametro> findByIdModulo(Long idModulo) {
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
    public Map<String,Parametro> findByIndiceModulo(String indiceModulo) {
        Map<String,Parametro> mapParametros = new HashMap<>();         
        Modulo modulo = moduloServicio.findByIndice(indiceModulo);
        List <Parametro> parametros = parametroRepositorio.findByIdModulo(modulo.getIdModulo());
        for (Parametro parametro : parametros) {            
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
    public Parametro findByIdModuloAndIndice(String indiceModulo, String indiceParametro) {
        Modulo modulo = moduloServicio.findByIndice(indiceModulo);
        return parametroRepositorio.findByIdModuloAndIndice(modulo.getIdModulo(), indiceParametro);
    }
}
