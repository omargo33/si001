package com.qapaq.gs00100.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qapaq.gs00100.jpa.model.Menu;
import com.qapaq.gs00100.jpa.queries.MenuRepositorio;


/**
 * Objeto para dar soporte a servicio REST de modulo
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-12
 * 
 */
@Service
@Transactional("gs001001TransactionManager")
public class MenuServicio {

    private final MenuRepositorio menuRepositorio;

    @Autowired
    public MenuServicio(MenuRepositorio menuRepositorio) {
        this.menuRepositorio = menuRepositorio;
    }


    /**
     * Metodo para mostrar todos los menus y paginar.
     * 
     */
    public List<Menu> findAllMenus(Pageable pageable) {
        return menuRepositorio.findAllByEstadoNot(pageable);



    

    
}
