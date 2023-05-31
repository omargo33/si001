package com.qapaq.gs00101.http.request;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapaq.gs00101.jpa.model.Parametro;
import com.qapaq.gs00101.servicio.ParametroServicio;
import com.qapaq.http.request.ComonControlador;

import lombok.RequiredArgsConstructor;

/**
 * Objeto para dar soporte a servicio REST de parametro
 * 
 * @author o.velez@qapaq.io
 * @since 2020-09-11
 * 
 */
@RestController
@RequestMapping(value = "/parametros")
@RequiredArgsConstructor
public class ParametroControlador extends ComonControlador {

   @Autowired
   ParametroServicio parametroService;

   
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
/* 
private void d(){

   KeycloakAuthenticationToken authentication = 
   (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

 Principal principal = (Principal) authentication.getPrincipal();

 String userIdByMapper = "";

 if (principal instanceof KeycloakPrincipal) {
     KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
     IDToken token = kPrincipal.getKeycloakSecurityContext().getIdToken();
     userIdByMapper = token.getOtherClaims().get("user_id").toString();
 }

}*/

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
