package com.qapaq.ca00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.ca00100.jpa.model.ParametroCat;

public interface ParametroRepositorioCat extends JpaRepository<ParametroCat, Long> {

        /**
         * Metodo para buscar un objeto por id_modulo y por indice.
         */
        public ParametroCat findByIdModuloAndIndice(Long idModulo, String indice);

        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        public List<ParametroCat> findAll();

        /**
         * Metodo para buscar un objeto por id_modulo.
         * 
         * @param id
         * @return
         */
        public List<ParametroCat> findByIdModulo(Long idModulo);
}