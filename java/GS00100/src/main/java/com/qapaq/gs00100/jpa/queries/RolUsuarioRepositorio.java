package com.qapaq.gs00100.jpa.queries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.RolUsuario;

@Repository
public interface RolUsuarioRepositorio extends JpaRepository<RolUsuario, Long> {

        /**
         * Metodo para buscar un objeto por id.
         * 
         * @param id
         * @return
         */
        public RolUsuario findByIdRolUsuario(Long id);

        /**
         * Metodo para buscar todos los objetos.
         * 
         * @return
         */
        public List<RolUsuario> findAll();

        /**
         * Metodo para buscar un objeto por idUsuario.
         * 
         * @param id
         * @return
         */
        public RolUsuario findByIdUsuario(Long idUsuario);
}
