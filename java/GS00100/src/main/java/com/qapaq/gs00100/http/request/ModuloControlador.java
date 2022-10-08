package com.qapaq.gs00100.http.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.gs00100.jpa.model.Modulo;
import com.qapaq.gs00100.servicio.ModuloService;

/**
 * Objeto para dar soporte a servicio REST de modulo
 * 
 * @author o.velez@qapaq.io
 * @date 2020-09-11
 * 
 */
@RestController
@RequestMapping(value = "/modulos")
public class ModuloControlador {

   @Autowired
   ModuloService moduloService;

   /**
    * Metodo para crear la clase.
    * 
    */
   public ModuloControlador(ModuloService moduloService) {
      this.moduloService = moduloService;
   }

   /**
    * Metodo para mostrar todos los modulos.
    */
   @GetMapping("/")
   public List<Modulo> getAllModulos() {
      return moduloService.findAllModulos();
   }

   /**
    * Metodo para mostrar un modulo por id.
    */
   @GetMapping("/{id}")
   public Modulo getModuloById(@PathVariable("id") String id) {
      return moduloService.findByIdModulo(id);
   }

   /**
    * Metodo para crear un modulo.
    * 
    * @param modulo
    */
   @PostMapping
   public void createModulo(@RequestBody Modulo modulo) {
      moduloService.createModulo("", modulo);
   }

}
