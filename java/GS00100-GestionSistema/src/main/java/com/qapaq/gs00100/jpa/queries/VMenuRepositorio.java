package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qapaq.gs00100.jpa.model.VMenu;

/**
 * Interface para la clase VMenu.
 * 
 * @author o.velez@qapaq.io
 * @date 2023-03-10
 */
public interface VMenuRepositorio extends JpaRepository<VMenu, Long> {

        /**
         * Metodo para buscar los objetos por nick y ordenados por orden.
         */
        public List<VMenu> findByNickOrderByOrdenAsc(String nick);

        /**
         * Metodo para buscar todos los objetos y ordenados por orden.
         */
        public List<VMenu> findAllByOrderByOrdenAsc();
}