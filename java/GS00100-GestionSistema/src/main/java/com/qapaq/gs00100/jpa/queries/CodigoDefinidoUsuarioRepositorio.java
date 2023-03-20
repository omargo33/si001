package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.gs00100.jpa.model.CodigoDefinidoUsuario;

/**
 * Interface para la clase CodigoDefinidoUsuario.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
public interface CodigoDefinidoUsuarioRepositorio extends JpaRepository <CodigoDefinidoUsuario, Long>{

        /**
        * Metodo para buscar un objeto por id y con estado distinto a eliminado (X)
        * 
        * @param id
        * @return
        */
        @Query("SELECT c FROM CodigoDefinidoUsuario c WHERE c.idCodigoDefinidoUsuario = ?1 AND c.estado <> 'X'")
        public CodigoDefinidoUsuario findByIdCodigoDefinidoUsuario(Long id);
        
        /**
        * Metodo para buscar todos los objetos.
        * 
        * @return
        */
        @Query("SELECT c FROM CodigoDefinidoUsuario c WHERE c.estado <> 'X'")
        public List<CodigoDefinidoUsuario> findAll();
        
        /**
        * Metodo para buscar un objeto por nombre.
        *  
         * @param nombre
         * @param idModulo
         * @param grupo
         * @return
         */
        @Query("SELECT c FROM CodigoDefinidoUsuario c WHERE c.nombre = ?1 AND c.idModulo = ?2 AND c.grupo = ?3 AND c.estado <> 'X'")
        public CodigoDefinidoUsuario findByNombreAndIdModuloAndGrupo(String nombre, Long idModulo, String grupo);

        /**
         * Metodo para buscar un objetos por id_modulo y grupo.
         * 
         * @param idModulo
         * @param grupo
         * @return
         */
        @Query("SELECT c FROM CodigoDefinidoUsuario c WHERE c.idModulo = ?1 AND c.grupo = ?2 AND c.estado <> 'X'")
        public List<CodigoDefinidoUsuario> findByIdModuloAndGrupo(Long idModulo, String grupo);            
}
