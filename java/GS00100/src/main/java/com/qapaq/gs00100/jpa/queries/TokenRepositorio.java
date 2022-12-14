package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.qapaq.gs00100.jpa.model.Token;

/**
 * Repositorio JPA para la entidad Token.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-10-30
 * 
 */
@Repository
public interface TokenRepositorio extends PagingAndSortingRepository<Token, Long> {
        /**
         * Metodo para buscar un objeto por id y con estado distinto a eliminado (X).
         * 
         * @param id
         * @return
         * 
         */
        @Query("SELECT t FROM Token t WHERE t.idToken = ?1 AND t.estado <> 'X'")
        public Token findByIdToken(Long id);

        /**
         * Metodo para buscar un objeto por id_usuario.
         * 
         * @param indice
         * @return
         */
        @Query("SELECT t FROM Token t WHERE t.idUsuario = ?1 AND t.estado <> 'X'")
        public List<Token> findByIdUsuario(Long idUsuario, Pageable pageable);

        /**
         * Metodo para buscar un objeto por socialNick y tipo.
         *          
         */
        @Query("SELECT t FROM Token t WHERE t.socialNick = ?1 AND t.tipo = ?2 AND t.estado <> 'X'")
        public Token findBySocialNickAndTipo(String socialNick, String tipo);

        /**
         * Metodo para buscar un objeto por id_usuario, tipo y con estado distinto a
         * eliminado (X).
         */
        @Query("SELECT t FROM Token t WHERE t.idUsuario = ?1 AND t.tipo = ?2 AND t.estado <> 'X'")
        public Token findByIdUsuarioAndTipo(Long idUsuario, String tipo);
        

        /**
         * Metodo para buscar un objeto por correo y con estado distinto a eliminado
         * (X).
         */
        @Query("SELECT t FROM Token t WHERE t.correo = ?1 AND t.estado <> 'X'")
        public Token findByCorreo(String correo);

        /**
         * Metodo para cambiar el borrado por id. Cambiando estado a X.
         */
        @Modifying
        @Query("UPDATE Token t SET t.estado = 'X' WHERE t.idToken = ?1")
        public void deleteByIdToken(Long idToken);

        /**
         * Metodo para validar si existe un  un token por social_nick y tipo, devuelve un valor booleano.
         */
        @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Token t WHERE t.socialNick = ?1 AND t.tipo = ?2 AND t.estado <> 'X'")
        public boolean existsBySocialNickAndTipo(String socialNick, String tipo);

        /**
         * Metodo para validar si existe un  un token por social_nick, tipo con id_usuario distinto, devuelve un valor booleano.
         */
        @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Token t WHERE t.socialNick = ?1 AND t.tipo = ?2 AND t.idUsuario <> ?3 AND t.estado <> 'X'")
        public boolean existsBySocialNickAndTipoAndIdUsuario(String socialNick, String tipo, Long idUsuario);
}
