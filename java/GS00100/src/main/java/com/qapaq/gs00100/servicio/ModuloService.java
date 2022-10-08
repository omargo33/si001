package com.qapaq.gs00100.servicio;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00100.jpa.model.Modulo;
import com.qapaq.gs00100.jpa.queries.ModuloRepositorio;
import com.qapaq.gs00100.seguridad.AESUtil;

import lombok.extern.slf4j.Slf4j;


/**
 * Objeto para dar soporte a servicio REST de modulo
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-12
 * 
 */
@Service
@Transactional
@Slf4j
public class ModuloService {

    private final ModuloRepositorio moduloRepositorio;
    
    @Value("${app.name}")
    String appName;

    @Value("${app.version}")
    String appVersion;    

    /**
     * Metodo para crear los repositorios.
     * @param moduloRepositorio
     *
     */
    public ModuloService(ModuloRepositorio moduloRepositorio) {
        this.moduloRepositorio = moduloRepositorio;
    }

    /**
     * Metodo para mostrar todos los modulos.
     */
    public List<Modulo> findAllModulos() {
        return moduloRepositorio.findAll();
    }

    /**
     * Metodo para mostrar un modulo por id.
     * 
     * @param id
     */
    public Modulo findByIdModulo(String id) {        
        long indice = 0;
        try{
            indice = Long.valueOf(AESUtil.desencriptar(id));
        }catch(Exception e){
            indice = 0;
            log.warn(e.toString());
        }
        return moduloRepositorio.findByIdModulo(indice);
    }

    /**
     * Metodo para crear un modulo.
     *  
     */
    public Modulo createModulo(String usuario, Modulo modulo) {
        modulo.setEstado(StringUtils.truncate("A", 8));        
        modulo.setUsuario(StringUtils.truncate(usuario, 128));        
        modulo.setUsuarioPrograma(StringUtils.truncate(String.format("%s %s", appName, appVersion), 256));
        return moduloRepositorio.save(modulo);
    }    

    /**
     * 
     */
}