package com.qapaq.ga00100.http.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;

import com.qapaq.http.request.ComonControlador;
import com.qapaq.ga00100.servicio.ArchivoServicio;
import com.qapaq.ga00100.jpa.model.Archivo;

/**
 * Clase para controlar las peticiones de las ciudades.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 * @see https://www.youtube.com/watch?v=VVn9OG9nfH0
 * @see security
 * 
 */
@RestController
@RequestMapping(value = "/archivo")
//public class ArchivoControlador extends ComonControlador {
public class ArchivoControlador  extends ComonControlador {
    
    @Autowired
    public ArchivoServicio archivoServicio;

    @Value("${spring.application.name}")
    private String appName;

    /**
     * Metodo para obtener todos los archivos.
     * 
     * @return
     */
    @GetMapping("/")
    public List<Archivo> getAllArchivos(Pageable pageable) {
        return archivoServicio.findAllArchivo(pageable);
    }

    /**
     * Metod para obtener un archivo por id_archivo
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Archivo getArchivo(@PathVariable Long id) {
        return archivoServicio.findByIdArchivo(id);
    }

    /**
     * Metodo para obtener todos los archivos paginados buscados por nonbre
     * 
     * @return
     */
    @GetMapping("/nombre={nombre}")
    public List<Archivo> getAllArchivosByNombre(@PathVariable String nombre, Pageable pageable) {
        return archivoServicio.findAllArchivoByNombre(nombre, pageable);
    }

    /**
     * Metodo para borrar un archivo por id_archivo
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArchivo(@RequestBody @PathVariable Archivo archivo, HttpServletRequest request) {        
        archivoServicio.deleteByIdArchivo(archivo.getIdArchivo(), evaluarUsuario(request), getUsuarioPrograma(archivo.getUsuarioPrograma()));
    }
}