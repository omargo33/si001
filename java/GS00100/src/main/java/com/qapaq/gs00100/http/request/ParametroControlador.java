package com.qapaq.gs00100.http.request;

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

import com.qapaq.gs00100.jpa.model.Parametro;
import com.qapaq.gs00100.servicio.ParametroServicio;
import com.qapaq.http.request.ComonControlador;

/**
 * Objeto para dar soporte a servicio REST de parametro
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-11
 * 
 */
@RestController
@RequestMapping(value = "/parametros")
public class ParametroControlador extends ComonControlador {

   ParametroServicio parametroService;

   /**
    * Metodo para crear la clase.
    * 
    * @param parametroService
    */
   @Autowired
   public ParametroControlador(ParametroServicio parametroService) {
      this.parametroService = parametroService;
   }

   /**
    * Metodo para mostrar todos los parametros.
    */
   /*@GetMapping("/")
   public List<Parametro> getAllParametros(Pageable pageable) {
      return parametroService.findAllParametros(pageable);
   }*/

   /**
    * Metodo para mostrar un parametro por id.
    */
   /*@GetMapping("/{id}")
   public Parametro getParametroById(@PathVariable Long id) {
      return parametroService.findByIdParametro(id);
   }*/

   /**
    * Metodo para buscar lista de parametros por indice.
    *
    * @param indice
    * @param pageable
    * @return
    */
   @GetMapping("/indice={indice}")
   public List<Parametro> getParametroByIndice(@PathVariable Long indice) {
      return parametroService.findByIdModulo(indice);
   }

   /**
    * Metodo para buscar lista de parametros por nombre.
    *
    * @param nombre
    * @param pageable
    * @return
    */
   /*@GetMapping("/nombre={nombre}")
   public List<Parametro> getParametroByNombre(@PathVariable String nombre, Pageable pageable) {
      return parametroService.findByNombreParametro(nombre, pageable);
   }*/

   /**
    * Metodo para crear un parametro.
    * 
    * @param parametro
    * @return
    */
   /*@PostMapping(value = "/")
   public Parametro createMonitor(@Valid @RequestBody Parametro parametro, HttpServletRequest request) {
      return parametroService.guardarParametro(parametro, evaluarUsuario(request), getUsuarioPrograma(parametro.getUsuarioPrograma()));
   }*/

   /**
    * Metodo para actualizar un parametro.
    * 
    * @param parametro
    * @return
    */
   /*@PutMapping(value = "/")   
   public Parametro updateMonitor(@Valid @RequestBody Parametro parametro, HttpServletRequest request) {
      return parametroService.guardarParametro(parametro, evaluarUsuario(request), getUsuarioPrograma(parametro.getUsuarioPrograma()));
   }*/

   /**
    * Metodo para borrar un parametro.
    *
    * @param id
    */
   /*@DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteParametro(@PathVariable Long id) {
      parametroService.borrarParametro(id);
   }*/
}
