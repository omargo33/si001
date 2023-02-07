package com.qapaq.gs00100.jpa.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qapaq.gs00100.jpa.model.Menu;

import java.util.List;

/**
 * Interface para la clase Menu.
 * 
 * @author o.velez@qapaq.io
 * @date 2022-09-10
 * 
 */
@Repository
public interface MenuRepositorio extends JpaRepository<Menu, Long> {

    /**
     * Metodo para buscar un objeto por id.
     * 
     * @param id
     * @return
     */
    public Menu findByIdMenu(Long id);

    /**
     * Metodo para buscar todos los objetos.
     * 
     * @return
     */
    public List<Menu> findAll();

    /**
     * Metodo para buscar por id_modulo con estado sea diferente a 'X' y paginado.
     */
    
    
    
    
    
    /**
     * Metodo para buscar un objeto por indice.
     * 
     * @param indice
     * @return
     */
    public Menu findByIndice(String indice);
}
