package com.qapaq.gs00101.http.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.gs00101.jpa.model.Menu;
import com.qapaq.gs00101.servicio.MenuServicio;
import com.qapaq.http.request.ComonControlador;

/**
 * Objeto para dar soporte a servicio REST de Menu
 * 
 * @author o.velez@qapaq.io
 * @since 2020-09-11
 * 
 */
@RestController
@RequestMapping(value = "/v1/menus")
public class MenuControlador extends ComonControlador {

   @Autowired
   MenuServicio menuService;
   
   /**
    * Metodo para mostrar todos los Menus.
    */
   @GetMapping("/")
   public List<Menu> getAllMenus(Pageable pageable) {
      return menuService.findAllMenus(pageable);
   }

   /**
    * Metodo para mostrar un Menu por id.
    */
   @GetMapping("/{id}")
   public Menu getMenuById(@PathVariable Long id) {
      return menuService.findMenuById(id);
   }

   /**
    * Metodo para buscar lista de Menus por indice.
    *
    * @param indice
    * @param pageable
    * @return
    */
   @GetMapping("/indice={indice}")
   public List<Menu> getMenuByIndice(@PathVariable String indice, Pageable pageable) {      
      return menuService.findAllMenusByIndice(indice, pageable);
   }

   /**
    * Metodo para buscar lista de Menus por nombre.
    *
    * @param nombre
    * @param pageable
    * @return
    */
   @GetMapping("/nombre={nombre}")
   public List<Menu> getMenuByNombre(@PathVariable String nombre, Pageable pageable) {
      return menuService.findAllMenusByNombre(nombre, pageable);
   }

   /**
    * Metodo para crear un Menu.
    * 
    * @param Menu
    * @return
    */
   @PostMapping(value = "/")
   public Menu createMonitor(@Valid @RequestBody Menu menu, HttpServletRequest request) {
      return menuService.guardarMenu(menu, evaluarUsuario(request), getUsuarioPrograma(menu.getUsuarioPrograma()));
   }

   /**
    * Metodo para actualizar un Menu.
    * 
    * @param Menu
    * @return
    */
   @PutMapping(value = "/")   
   public Menu updateMonitor(@Valid @RequestBody Menu menu, HttpServletRequest request) {
      return menuService.guardarMenu(menu, evaluarUsuario(request), getUsuarioPrograma(menu.getUsuarioPrograma()));
   }

   /**
    * Metodo para borrar un Menu.
    *
    * @param id
    */
   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteMenu(@PathVariable Long id) {
      menuService.borrarMenu(id);
   }
}
