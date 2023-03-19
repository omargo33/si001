package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.VMenu;

@Repository
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