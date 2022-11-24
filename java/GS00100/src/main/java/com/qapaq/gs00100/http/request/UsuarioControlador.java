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

import com.qapaq.gs00100.jpa.model.Usuario;
import com.qapaq.gs00100.servicio.UsuarioServicio;
import com.qapaq.http.request.ComonControlador;

/**
 * Objeto para dar soporte a servicio REST de modulo
 * 
 * @author o.velez@qapaq.io
 * @date 2020-10-30
 * 
 */
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioControlador extends ComonControlador {

    UsuarioServicio usuarioService;

    @Autowired
    public UsuarioControlador(UsuarioServicio usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Metodo para mostrar todos los usuarios de forma paginada.
     */
    @GetMapping("/")
    public List<Usuario> getAllUsuarios(Pageable pageable) {
        return usuarioService.findAllUsuarios(pageable);
    }

    /**
     * Metodo para mostrar un usuario por id.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarioService.findByIdUsuario(id);
    }

    /**
     * Metodo para mostrar un usuario por nick
     */
    @GetMapping("/nick={nick}")
    public Usuario getUsuarioByNick(@PathVariable String nick) {
        return usuarioService.findByNick(nick);
    }

    /**
     * Metodo para buscar por nombre y apellido continuo.
     *
     * @param usuario
     * @return
     * 
     */
    @GetMapping("/nombres={nombres}")
    public List<Usuario> getUsuarioByNombreApellido(@PathVariable String nombres, Pageable pageable) {
        return usuarioService.findByNombreApellido(nombres, pageable);
    }

    /**
     * Metodo para crear un usuario.
     * 
     * @param usuario
     * @param request
     * @return
     */
    @PostMapping("/")
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario, HttpServletRequest request) {
        return usuarioService.guardarUsuario(usuario, evaluarUsuario(request),
                getUsuarioPrograma(usuario.getUsuarioPrograma()));
    }

    /**
     * Metodo para actualizar un usuario.
     * 
     * @param usuario
     * @param request
     * @return
     */
    @PutMapping("/")
    public Usuario updateUsuario(@Valid @RequestBody Usuario usuario, HttpServletRequest request) {
        return usuarioService.guardarUsuario(usuario, evaluarUsuario(request),
                getUsuarioPrograma(usuario.getUsuarioPrograma()));
    }

    /**
     * Metodo para borrar un usuario por id.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }
}
