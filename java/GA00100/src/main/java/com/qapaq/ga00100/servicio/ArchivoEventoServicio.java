package com.qapaq.ga00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ga00100.jpa.model.Archivo;
import com.qapaq.ga00100.jpa.model.ArchivoEvento;
import com.qapaq.ga00100.jpa.queries.ArchivoRepositorio;
import com.qapaq.ga00100.jpa.queries.ArchivoEventoRepositorio;
import org.apache.commons.lang3.StringUtils;

/**
 * Clase de servicio crud para Archivo repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-03-01
 * 
 */

@Service
@Transactional
public class ArchivoEventoServicio {
    
    private final ArchivoEventoRepositorio archivoEventoRepositorio;


    /**
     * Constructor de la clase.
     * 
     * @param archivoRepositorio
     * @param archivoEventoRepositorio
     */
    public ArchivoEventoServicio(ArchivoEventoRepositorio archivoEventoRepositorio) {
        this.archivoEventoRepositorio = archivoEventoRepositorio;
    }


    /**
     * Metodo para crear una entradad de archivo evento.
     * 
     * @param idArchivo
     * @param informacion
     * @param tipo -> ConstantesGA00100.ARCHIVO_EVENTO_ 
     * @param usuario
     * @param usuarioPrograma
     * @return archivoEvento
     */
    public ArchivoEvento saveArchivoEvento(Long idArchivo, String informacion,String tipo, String usuario, String usuarioPrograma) {
        ArchivoEvento archivoEvento = new ArchivoEvento();
        archivoEvento.setIdArchivo(idArchivo);
        archivoEvento.setInformacion(StringUtils.truncate(informacion, 512));
        archivoEvento.setTipo(StringUtils.truncate(tipo, 8));
        archivoEvento.setUsuario(StringUtils.truncate(usuario, 128));
        archivoEvento.setUsuarioFecha(new Date());
        archivoEvento.setUsuarioPrograma(StringUtils.truncate(usuarioPrograma, 256));
        return archivoEventoRepositorio.save(archivoEvento);
    }     
}
