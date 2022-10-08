package com.qapaq.gs00100.jpa.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.VUsers;


/**
 * Interface para la clase VUsers.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-11
 * 
 */
@Repository
public interface VUsersRepositorio extends JpaRepository<VUsers, Long> {

    /**
     * Metodo para buscar un objeto por id_usuario.
     * 
     * @param id
     * @return
     */
    public VUsers findByIdUsuario(Long id);

    /**
     * Metodo para buscar un objeto por NAME.
     * 
     * @param indice
     * @return
     */
    public VUsers findByName(String name);    
}
