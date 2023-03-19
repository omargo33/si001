package com.qapaq.si00100.servicio;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qapaq.si00100.jpa.model.Proyecto;
import com.qapaq.si00100.jpa.queries.ProyectoRepositorio;

/**
 * Clase de servicio CRUD para Proyecto repositorio.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-14
 * 
 */
@Service
@Transactional
public class ProyectoServicio {

    private final ProyectoRepositorio proyectoRepositorio;

    /**
     * Constructor de la clase.
     * 
     * @param proyectoRepositorio
     */
    public ProyectoServicio(ProyectoRepositorio proyectoRepositorio) {
        this.proyectoRepositorio = proyectoRepositorio;
    }

    /**
     * Metodo guardar proyecto.
     * 
     * @param proyecto
     * @return
     */
    public Proyecto saveProyecto(Proyecto proyecto, String usuario, String usuarioPrograma) {
        proyecto.setEstado("A");
        proyecto.setUsuario(usuario);
        proyecto.setUsuarioFecha(new Date());
        proyecto.setUsuarioPrograma(usuarioPrograma);
        return proyectoRepositorio.save(proyecto);
    }

    /**
     * Metodo listar todos los proyectos de manera paginada
     */
    public List<Proyecto> findAllProyecto(Pageable pageable) {
        return proyectoRepositorio.findAllProyectos(pageable);
    }

    /**
     * Metodo para buscar un proyecto por id_proyecto.
     */
    public Proyecto findProyectoById(Long id) {
        return proyectoRepositorio.findByIdProyecto(id);
    }

    /**
     * Metodo para buscar un proyecto por nombre.
     */
    public List<Proyecto> findProyectoByNombre(String nombre, Pageable pageable) {
        return proyectoRepositorio.findAllByNombre(nombre, pageable);
    }

    /**
     * Metodo para borrar un proyecto por id_proyecto.
     */
    public void deleteProyectoById(Long id) {
        proyectoRepositorio.deleteByIdProyecto(id);
    }
}