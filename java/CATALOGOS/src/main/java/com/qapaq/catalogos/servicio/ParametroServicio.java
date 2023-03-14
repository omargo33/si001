package com.qapaq.catalogos.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.catalogos.jpa.model.Modulo;
import com.qapaq.catalogos.jpa.model.Parametro;
import com.qapaq.catalogos.jpa.queries.ParametroRepositorio;

import lombok.extern.slf4j.Slf4j;

/**
 * Objeto para dar soporte a servicio REST de Parametro.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-12-08
 * 
 */
@Service
@Transactional
@Slf4j
public class ParametroServicio {

    private ParametroRepositorio parametroRepositorio;

    private ModuloServicio moduloServicio;

    @Autowired
    public ParametroServicio(ParametroRepositorio parametroRepositorio, ModuloServicio moduloServicio) {
        this.parametroRepositorio = parametroRepositorio;
        this.moduloServicio = moduloServicio;
    }    

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
        log.info("indiceModulo {}",indiceModulo);
        Map<String,Parametro> mapParametros = new HashMap<>();         
        Modulo modulo = moduloServicio.findByIndice(indiceModulo);
        log.info(modulo.toString());
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
