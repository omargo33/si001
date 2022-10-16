package com.qapaq.si00100.http.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.si00100.jpa.exception.ForeignKeyException;
import com.qapaq.si00100.jpa.model.Proyecto;
import com.qapaq.si00100.servicio.ProyectoServicio;

/**
 * Clase para controlar las peticiones de CRUD para servicio de proyectos.
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-04
 * 
 */
@RestController
@RequestMapping(value = "/proyectos")
public class ProyectoControlador extends ComonControlador{

        private final ProyectoServicio proyectoServicio;

        @Value("${app.name}")
        private String appName;

        @Value("${app.version}")
        private String appVersion;

        @Autowired
        public ProyectoControlador(ProyectoServicio proyectoServicio) {
            this.proyectoServicio = proyectoServicio;
        }
        
        /**
        * Metodo para obtener todos los proyectos.
        * 
        * @return
        */
        @GetMapping("/")
        public List<Proyecto> getAllProyecto(Pageable pageable) {
            return proyectoServicio.findAllProyecto(pageable);
        }
        
        /**
        * Metodo para obtener un proyecto por id.
        * 
        * @param id
        * @return
        */
        @GetMapping("/{id}")
        public Proyecto getProyectoById(@PathVariable("id") Long id) {
            return proyectoServicio.findProyectoById(id);
        }

        /**
         * Metodo para obtener un proyecto por nombre y paginado.
         * @param proyecto
         * @return
         */
        @GetMapping("/nombre={nombre}")
        public List<Proyecto> getProyectoByNombre(@PathVariable("nombre") String nombre, Pageable pageable) {
            return proyectoServicio.findProyectoByNombre(nombre, pageable);
        }
        
        /**
        * Metodo para guardar un proyecto.
        * 
        * @param proyecto
        * @return
        */
        @PostMapping("/")
        public Proyecto saveProyecto(@Valid @RequestBody Proyecto proyecto) {
            return proyectoServicio.saveProyecto(proyecto, "getUsuario()", appName+" "+ appVersion);
        }
        
        /**
        * Metodo para actualizar un proyecto.
        * 
        * @param proyecto
        * @return
        */
        @PutMapping("/")
        public Proyecto updateProyecto(@Valid @RequestBody Proyecto proyecto) {
            return proyectoServicio.saveProyecto(proyecto, "getUsuario()", appName+" "+ appVersion);
        }
        
        /**
        * Metodo para eliminar un proyecto.
        * 
        * @param id
        * @return
        */
        @DeleteMapping("/{id}")
        public void deleteMonitorComando(@PathVariable Long id) throws ForeignKeyException {        
            proyectoServicio.deleteProyectoById(id);
        }    
}