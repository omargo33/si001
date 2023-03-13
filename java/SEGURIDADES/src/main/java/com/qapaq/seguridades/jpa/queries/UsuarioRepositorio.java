package com.qapaq.seguridades.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.seguridades.jpa.model.Usuario;

/**
 * Clase de repositorio de la entidad Usuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-30
 * 
 */
@Repository
public interface UsuarioRepositorio extends PagingAndSortingRepository<Usuario, Long> {

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
        public List<Usuario> findAllByEstadoNot(Pageable pageable);

        /**
         * Metodo para buscar un objeto por nick.
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT u FROM Usuario u WHERE u.nick = ?1 AND u.estado <> 'X'")
        public Usuario findByNick(String nick);

        /**
         * Metodo para buscar un objeto por like nombre o apellido.
         * 
         * @param nombreApellido
         * @return
         */
        @Query("SELECT u FROM Usuario u WHERE concat(u.nombre, u.apellido) LIKE %?1% AND u.estado <> 'X'")
        public List<Usuario> findByNombreApellido(String nombreApellido, Pageable pageable);

        /**
         * Metodo para validar si existe un usuario por nick y devuelve el valor
         * booleano.
         * 
         */
        @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.nick = ?1 AND u.estado <> 'X'")
        public boolean existsByNick(String nick);

        /**
         * Metodo para validar si existe un usuario por nick y diferente idUsuario y
         * devuelve el valor booleano.
         * 
         */
        @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.nick = ?1 AND u.idUsuario <> ?2 AND u.estado <> 'X'")
        public boolean existsByNickAndIdUsuarioNot(String nick, Long idUsuario);

        /**
         * Metodo para un borrado logico de un objeto.
         */                
        @Modifying
        @Query("UPDATE Usuario u SET u.estado = 'X' WHERE u.idUsuario = ?1")
        public void deleteByIdUsuario(Long idUsuario);
}