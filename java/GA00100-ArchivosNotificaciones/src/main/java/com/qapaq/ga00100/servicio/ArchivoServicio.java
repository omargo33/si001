package com.qapaq.ga00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.ga00100.jpa.model.Archivo;
import com.qapaq.ga00100.jpa.queries.ArchivoRepositorio;

import com.qapaq.ga00100.ConstantesGA00100;

/**
 * Clase de servicio crud para Archivo repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-03-01
 * 
 */
@Service
@Transactional
public class ArchivoServicio {

    private final ArchivoRepositorio archivoRepositorio;
    private final ArchivoEventoServicio archivoEventoServicio;
    

    /**
     * Constructor de la clase.
     * 
     * @param archivoRepositorio
     * @param archivoEventoRepositorio
     */
    public ArchivoServicio(ArchivoRepositorio archivoRepositorio, ArchivoEventoServicio archivoEventoServicio) {
        this.archivoRepositorio = archivoRepositorio;
        this.archivoEventoServicio = archivoEventoServicio;
    }
    

    /**
     * Metodo guardar archivo se crea usuario_fecha.
     * 
     * @param archivo
     * @param usuario
     * @param usuarioPrograma
     * @return archivo
     */
    public Archivo saveArchivo(Archivo archivo, String usuario, String usuarioPrograma) {
        archivo.setUsuario(usuario);
        archivo.setUsuarioFecha(new Date());
        archivo.setUsuarioPrograma(usuarioPrograma);
        return archivoRepositorio.save(archivo);
    }

    /**
     * Metodo buscar todos los archivos de manera paginada.
     */
    public List<Archivo> findAllArchivo(Pageable pageable) {
        return archivoRepositorio.findAllPage(pageable);
    }

    /**
     * Metodo buscar todos los archivos de manera paginada por nombre
     */
    public List<Archivo> findAllArchivoByNombre(String nombre, Pageable pageable) {
        return archivoRepositorio.findAllPageByNombre(nombre, pageable);
    }

    /**
     * Metodo para buscar un unico archivo por id_archivo
     */
    public Archivo findByIdArchivo(Long idArchivo) {
        return archivoRepositorio.findByIdArchivo(idArchivo);
    }

    /**
     * Metodo para borrar un archivo por id_archivo
     * 
     * @param idArchivo
     */
    public void deleteByIdArchivo(Long idArchivo, String usuario, String usuarioPrograma) {        
        archivoRepositorio.deleteByIdArchivo(idArchivo);
        archivoEventoServicio.saveArchivoEvento(idArchivo, "Archivo borrado",ConstantesGA00100.ARCHIVO_EVENTO_BORRADO, usuario,  usuarioPrograma);
    }
}
