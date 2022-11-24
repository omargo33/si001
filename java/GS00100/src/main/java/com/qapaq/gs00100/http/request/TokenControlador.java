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

import com.qapaq.gs00100.jpa.model.Token;
import com.qapaq.gs00100.servicio.TokenServicio;
import com.qapaq.http.request.ComonControlador;

/**
 * Objeto para dar soporte a servicio REST de tokens
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-30
 * 
 */
@RestController
@RequestMapping(value = "/tokens")
public class TokenControlador extends ComonControlador {

   TokenServicio tokenService;

   /**
    * Metodo para crear la clase.
    * 
    * @param tokenService
    */
   @Autowired
   public TokenControlador(TokenServicio tokenService) {
      this.tokenService = tokenService;
   }

   /**
    * Metodo para mostrar un token por id.
    */
   @GetMapping("/{id}")
   public Token getTokenById(@PathVariable Long id) {
      return tokenService.findByIdToken(id);
   }

   /**
    * Metodo para mostrar un token por id.
    */
    @GetMapping("/idUsuario={idUsuario}")
    public List<Token> getTokenById(@PathVariable Long idUsuario, Pageable pageable) {
       return tokenService.findByIdUsuario(idUsuario, pageable);
    }

   /**
    * Metodo para crear un token.
    * 
    * @param token
    * @return
    */
   @PostMapping(value = "/")
   public Token createToken(@Valid @RequestBody Token token, HttpServletRequest request) {
      return tokenService.guardarToken(token, evaluarUsuario(request), getUsuarioPrograma(token.getUsuarioPrograma()));
   }

   /**
    * Metodo para actualizar un token.
    * 
    * @param token
    * @return
    */
   @PutMapping(value = "/")   
   public Token updateToken(@Valid @RequestBody Token token, HttpServletRequest request) {
      return tokenService.guardarToken(token, evaluarUsuario(request), getUsuarioPrograma(token.getUsuarioPrograma()));
   }

   /**
    * Metodo para borrar un token.
    *
    * @param id
    */
   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteToken(@PathVariable Long id) {
      tokenService.deleteByIdToken(id);
   }
}
