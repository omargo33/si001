package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.gs00100.jpa.model.VGroups;


/**
 * Interface para la clase VGroups.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-11
 * 
 */
public interface VGroupsRepositorio extends JpaRepository <VGroups, Long>{

        
        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        public List<VGroups> findAll();
        
        /**
         * Metodo para buscar un objeto por NAME.
         * 
         * @param indice
         * @return
         */
        public VGroups findByName(String name);

        /**
         * Metodo para buscar un objetos por DESCRIPTION.
         * 
         */
        public List<VGroups> findByDescription(String description);
}
