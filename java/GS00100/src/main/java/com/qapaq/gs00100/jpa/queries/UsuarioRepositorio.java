package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

        /**
         * Metodo para buscar un objeto por id y con estado distinto a eliminado (X).
         * 
         * @param id
         * @return
         * 
         */
        @Query("SELECT u FROM Usuario u WHERE u.idUsuario = ?1 AND u.estado <> 'X'")
        public Usuario findByIdUsuario(Long id);

        
        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        @Query("SELECT u FROM Usuario u WHERE u.estado <> 'X'")
        public List<Usuario> findAll();

        /**
         * Metodo para buscar un objeto por nombre.
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT u FROM Usuario u WHERE u.nombre = ?1 AND u.estado <> 'X'")
        public Usuario findByNick(String nombre);
}