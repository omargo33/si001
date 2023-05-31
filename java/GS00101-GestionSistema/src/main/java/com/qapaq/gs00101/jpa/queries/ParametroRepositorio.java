package com.qapaq.gs00101.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.gs00101.jpa.model.Parametro;


/**
 * Interface para la clase Parametro.
 * 
 * @author o.velez@qapaq.io
 * @since 2023-03-19
 */
public interface ParametroRepositorio extends JpaRepository<Parametro, Long> {

        /**
         * Metodo para buscar un objeto por id_modulo y por indice.
         */
        public Parametro findByIdModuloAndIndice(Long idModulo, String indice);

        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        public List<Parametro> findAll();

        /**
         * Metodo para buscar un objeto por id_modulo.
         * 
         * @param id
         * @return
         */
        public List<Parametro> findByIdModulo(Long idModulo);
}