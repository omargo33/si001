package com.qapaq.gs00100.jpa.queries;

import com.qapaq.gs00100.jpa.model.Error;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface para la clase Error.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-09
 * 
 */
public interface ErrorRepositorio extends JpaRepository <Error, Long>{

        /**
         * Metodo para buscar un objeto por id.
         * 
         * @param id
         * @return
         */
        public Error findByIdError(Long id);

        
        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        public List<Error> findAll();
        
        /**
         * Metodo para buscar un objeto por indice.
         * 
         * @param indice
         * @return
         */
        public Error findByIndice(String indice);
}
