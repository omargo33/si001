package com.qapaq.gs00101.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00101.jpa.model.Menu;
import com.qapaq.gs00101.jpa.queries.MenuRepositorio;

import lombok.RequiredArgsConstructor;


/**
 * Objeto para dar soporte a servicio REST de modulo
 * 
 * @author o.velez@qapaq.io
 * @since 2020-09-12
 * 
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MenuServicio {

    @Autowired
    private final MenuRepositorio menuRepositorio;

    /**
     * Metodo para mostrar un menu por id.
     * 
     * @param pageable
     * @return
     */
    public Menu findMenuById(Long id) {
        return menuRepositorio.findByIdMenu(id);
    }

    /**
     * Metodo para mostrar todos los menus por nombre paginado y que estado no sea X.
     * 
     */
    public List<Menu> findAllMenusByNombre(String nombre, Pageable pageable) {
        return menuRepositorio.findAllByNombreLikeAndEstadoNot(nombre, pageable);
    }

    /**
     * Metodo para mostrar todos los menus, paginado por indice y que estado no sea X.
     *      
     */
    public List<Menu> findAllMenusByIndice(String indice, Pageable pageable) {
        return menuRepositorio.findAllByIndiceAndEstadoNot(indice, pageable);
    }

    /**
     * Metodo para mostrar todos los menus y paginar.
     * 
     */
    public List<Menu> findAllMenus(Pageable pageable) {
        return menuRepositorio.findAllByEstadoNot(pageable);
    }

    /**
     * Metodo para guardar el menu con usuario y usuario porgrama retorna el id del menu.
     * 
     */
    public Menu guardarMenu(Menu menu, String usuario, String usuarioPrograma) {
        menu.setEstado("A");
        menu.setUsuario(usuario);
        menu.setUsuarioPrograma(usuarioPrograma);
        menu.setUsuarioFecha(new Date());
        menuRepositorio.save(menu);
        return menu;
    }    
        

    /**
     * Metodo para borrar un menu por id.
     * 
     */
    public void borrarMenu(Long id) {
        Menu menu = menuRepositorio.findByIdMenu(id);
        menu.setEstado("X");        
        menu.setUsuarioFecha(new Date());
        menuRepositorio.save(menu);
    }
}
