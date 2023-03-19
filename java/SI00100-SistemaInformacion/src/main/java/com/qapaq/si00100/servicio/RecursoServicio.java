package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import com.qapaq.si00100.jpa.model.Recurso;
import com.qapaq.si00100.jpa.queries.RecursoRepositorio;

/**
 * Clase de servicio CRUD para Recurso repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-14
 * 
 */
@Service
@Transactional
public class RecursoServicio {

    private final RecursoRepositorio recursoRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param recursoRepositorio
     */
    public RecursoServicio(RecursoRepositorio recursoRepositorio) {
        this.recursoRepositorio = recursoRepositorio;
    }

    /**
     * Metodo guardar recurso.
     * 
     * @param recurso
     * @return
     */
    public Recurso saveRecurso(Recurso recurso, String usuario, String usuarioPrograma) {
        recurso.setEstado("A");
        recurso.setUsuario(usuario);
        recurso.setUsuarioFecha(new Date());
        recurso.setUsuarioPrograma(usuarioPrograma);
        return recursoRepositorio.save(recurso);
    }

    /**
     * Metodo listar todos los recursos de manera paginada
     */
    public List<Recurso> findAllRecurso(Pageable pageable) {
        return recursoRepositorio.findAllRecursos(pageable);
    }

    /**
     * Metodo para buscar un recurso por id_recurso.
     */
    public Recurso findRecursoById(Long id) {
        return recursoRepositorio.findByIdRecurso(id);
    }

    /**
     * Metodo para buscar un recurso por id_proyecto.
     */
    public List<Recurso> findRecursoByIdProyecto(Long idProyecto, Pageable pageable) {
        return recursoRepositorio.findAllByIdProyecto(idProyecto, pageable);
    }

    /**
     * Buscar recurso por nombre y por id_proyecto, ademas de paginado.     
     * 
     * @param nombre
     * @param idProyecto
     * @return
     */
    public List<Recurso> findRecursoByNombreAndIdProyecto(String nombre, Long idProyecto, Pageable pageable) {
        return recursoRepositorio.findRecursoByNombreAndIdProyecto(nombre, idProyecto, pageable);
    }

    /**
     * Metodo para borrar un recurso por id_recurso.
     */
    public void deleteRecursoById(Long id) {
        recursoRepositorio.deleteByIdRecurso(id);
    }
}
