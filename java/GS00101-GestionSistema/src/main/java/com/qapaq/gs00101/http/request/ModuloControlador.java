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

import com.qapaq.gs00101.jpa.model.Modulo;
import com.qapaq.gs00101.servicio.ModuloServicio;
import com.qapaq.http.request.ComonControlador;

import lombok.RequiredArgsConstructor;

/**
 * Objeto para dar soporte a servicio REST de modulo
 * 
 * @author o.velez@qapaq.io
 * @since 2020-09-11
 * 
 */
@RestController
@RequestMapping(value = "/modulos")
@RequiredArgsConstructor
public class ModuloControlador extends ComonControlador {

   @Autowired
   ModuloServicio moduloService;

   /**
    * Metodo para mostrar todos los modulos.
    */
   @GetMapping("/")
   public List<Modulo> getAllModulos(Pageable pageable) {
      return moduloService.findAllModulos(pageable);
   }

   /**
    * Metodo para mostrar un modulo por id.
    */
   @GetMapping("/{id}")
   public Modulo getModuloById(@PathVariable Long id) {
      return moduloService.findByIdModulo(id);
   }

   /**
    * Metodo para buscar lista de modulos por indice.
    *
    * @param indice
    * @param pageable
    * @return
    */
   @GetMapping("/indice={indice}")
   public List<Modulo> getModuloByIndice(@PathVariable String indice, Pageable pageable) {
      return moduloService.findByIndiceModulo(indice, pageable);
   }

   /**
    * Metodo para buscar lista de modulos por nombre.
    *
    * @param nombre
    * @param pageable
    * @return
    */
   @GetMapping("/nombre={nombre}")
   public List<Modulo> getModuloByNombre(@PathVariable String nombre, Pageable pageable) {
      return moduloService.findByNombreModulo(nombre, pageable);
   }

   /**
    * Metodo para crear un modulo.
    * 
    * @param modulo
    * @return
    */
   @PostMapping(value = "/")
   public Modulo createMonitor(@Valid @RequestBody Modulo modulo, HttpServletRequest request) {
      return moduloService.guardarModulo(modulo, evaluarUsuario(request), getUsuarioPrograma(modulo.getUsuarioPrograma()));
   }

   /**
    * Metodo para actualizar un modulo.
    * 
    * @param modulo
    * @return
    */
   @PutMapping(value = "/")   
   public Modulo updateMonitor(@Valid @RequestBody Modulo modulo, HttpServletRequest request) {
      return moduloService.guardarModulo(modulo, evaluarUsuario(request), getUsuarioPrograma(modulo.getUsuarioPrograma()));
   }

   /**
    * Metodo para borrar un modulo.
    *
    * @param id
    */
   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteModulo(@PathVariable Long id) {
      moduloService.borrarModulo(id);
   }
}
