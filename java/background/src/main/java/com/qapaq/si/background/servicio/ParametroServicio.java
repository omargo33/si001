package com.qapaq.si.background.servicio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qapaq.si.background.jpa.model.Modulo;
import com.qapaq.si.background.jpa.model.Parametro;
import com.qapaq.si.background.jpa.queries.ModuloRepositorio;
import com.qapaq.si.background.jpa.queries.ParametroRepositorio;
import org.springframework.beans.factory.annotation.Value;

/**
 * Clase para dar servicio de parametro.java
 * 
 * @author: o.velez@qapaq.io
 * @date: 2020/08/16
 * 
 */
@Service
@Transactional
public class ParametroServicio {

    @Value("${aplicacion.nombre}")
    String aplicacion;

    // Parametro servicio
    private ParametroRepositorio parametroRepositorio;

    private ModuloRepositorio moduloRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param parametroRepositorio
     * @param moduloRepositorio
     */
    public ParametroServicio(ParametroRepositorio parametroRepositorio, ModuloRepositorio moduloRepositorio) {
        this.parametroRepositorio = parametroRepositorio;
        this.moduloRepositorio = moduloRepositorio;
    }

    /**
     * Metodo para buscar un parametro por indice de parametro e indice de modulo.
     * 
     * buscar modulo por nombre aplicacion.
     * buscar parametro por indice y id_modulo.
     * 
     * @param indiceParametro     
     * @return
     */
    public Parametro buscarParametro(String indiceParametro) {
        Modulo modulo = moduloRepositorio.findByIndice(aplicacion);
        return parametroRepositorio.findByIndiceAndIdModulo(indiceParametro, modulo.getIdModulo());
    }
}
