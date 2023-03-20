package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.gs00100.jpa.model.Rol;

/**
 * Interface para la clase Rol.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-10
 */
public interface RolRepositorio extends JpaRepository <Rol, Long> {

        /**
         * Metodo para buscar un objeto por id y con estado distinto a eliminado (X).
         * 
         * @param id
         * @return
         */
        @Query("SELECT r FROM Rol r WHERE r.idRol = ?1 AND r.estado <> 'X'")
        public Rol findByIdRol(Long id);

        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        @Query("SELECT r FROM Rol r WHERE r.estado <> 'X'")
        public List<Rol> findAll();

        /**
         * Metodo para buscar un objeto por idModulo.
         * 
         * @param id
         * @return
         */
        @Query("SELECT r FROM Rol r WHERE r.idModulo = ?1 AND r.estado <> 'X'")
        public Rol findByIdModulo(Long idModulo);
}
