package com.qapaq.gs00100.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00100.jpa.model.Modulo;
import com.qapaq.gs00100.jpa.model.Parametro;
import com.qapaq.gs00100.jpa.queries.ParametroRepositorio;

/**
 * Objeto para dar soporte a servicio REST de Parametro.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-12-08
 * 
 */
@Service
@Transactional("gs001001TransactionManager")
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
        Map<String,Parametro> mapParametros = new HashMap<>();         
        Modulo modulo = moduloServicio.findByIndice(indiceModulo);
        List <Parametro> parametros = parametroRepositorio.findByIdModulo(modulo.getIdModulo());
        for (Parametro parametro : parametros) {            
            mapParametros.put(parametro.getIndice(), parametro);            
        }

        return mapParametros;
    }
}
