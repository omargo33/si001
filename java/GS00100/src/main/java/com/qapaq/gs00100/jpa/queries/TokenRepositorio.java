package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.Token;

@Repository
public interface TokenRepositorio extends JpaRepository<Token, Long> {
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
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        @Query("SELECT t FROM Token t WHERE t.estado <> 'X'")
        public List<Token> findAll();

        
        /**
         * Metodo para buscar un objeto por id_usuario.
         * 
         * @param indice
         * @return
         */
        @Query("SELECT t FROM Token t WHERE t.idUsuario = ?1 AND t.estado <> 'X'")
        public Token findByIdUsuario(Long indice);

        /**
         * Metodo para buscar un objeto por social_nick, tipo y  con estado distinto a eliminado (X).
         */
        @Query("SELECT t FROM Token t WHERE t.socialNick = ?1 AND t.tipo = ?2 AND t.estado <> 'X'")
        public Token findBySocialNickAndTipo(String socialNick, String tipo);

        /**
         * Metodo para buscar un objeto por correo y  con estado distinto a eliminado (X).
         */
        @Query("SELECT t FROM Token t WHERE t.correo = ?1 AND t.estado <> 'X'")
        public Token findByCorreo(String correo);

}
