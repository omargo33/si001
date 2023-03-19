package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.Permiso;

/**
 * Interface para la clase Permiso.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-10
 * 
 */
@Repository
public interface PermisoRepositorio extends JpaRepository<Permiso, Long> {

        /**
         * Metodo para buscar un objeto por id.
         * 
         * @param id
         * @return
         */
        public Permiso findByIdPermiso(Long id);

        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        public List<Permiso> findAll();

        /**
         * Metodo para buscar un objeto por idMenu.
         * 
         * @param id
         * @return
         */
        public Permiso findByIdMenu(Long idMenu);
}
        