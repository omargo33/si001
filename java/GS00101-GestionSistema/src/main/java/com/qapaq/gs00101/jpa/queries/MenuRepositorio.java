package com.qapaq.gs00101.jpa.queries;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qapaq.gs00101.jpa.model.Menu;

import java.util.List;

/**
 * Interface para la clase Menu.
 * 
 * @author o.velez@qapaq.io
 * @since 2022-09-10
 * 
 */
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
    @Query("SELECT m FROM Menu m WHERE m.estado <> 'X' ORDER BY m.idMenu")
    public List<Menu> findAllByEstadoNot(Pageable pageable);

    /**
     * Metodo para mostrar todos los menus like nombre paginado y que estado no sea X.
     * 
     */
    @Query("SELECT m FROM Menu m WHERE m.nombre LIKE %?1% AND m.estado <> 'X' ORDER BY m.idMenu")
    public List<Menu> findAllByNombreLikeAndEstadoNot(String nombre, Pageable pageable);
    
    
    /**
     * Metodo para buscar un objeto por indice paginado y que estado no sea 'X'.
     * 
     */
    @Query("SELECT m FROM Menu m WHERE m.indice = ?1 AND m.estado <> 'X' ORDER BY m.idMenu")
    public List<Menu> findAllByIndiceAndEstadoNot(String indice, Pageable pageable);

    /**
     * Metodo para buscar un objeto like indice y con estado distinto a eliminado
     * (X) y paginado.
     * 
     * @param indice
     * @return
     */
    @Query("SELECT m FROM Menu m WHERE m.indice LIKE %?1% AND m.estado <> 'X' ORDER BY m.idMenu")
    public List<Menu> findAllByIndiceLikeAndEstadoNot(String indice, Pageable pageable);
}
